package com.example.getrequestexample.discounts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getrequestexample.R;
import com.example.getrequestexample.data.Shop;
import com.example.getrequestexample.discountsInShop.DiscountsInSelectedShopActivity;
import com.example.getrequestexample.maps.MapsShopActivity;
import com.example.getrequestexample.utils.WorkWithTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiscountsFragment extends Fragment implements DiscountsContract.View {

    DiscountsContract.Presenter mPresenter;
    Logger log;

    ImageView imageView;
    TextView nameShopView, workingHoursView, shopStateView, typeShop;

    public static DiscountsFragment getInstance() {
        return new DiscountsFragment();
    }

    @Override
    public void setPresenter(DiscountsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shop_details_fragment, container, false);


        FloatingActionButton fabOpenMap =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_shop_on_map);
        FloatingActionButton fabOpenDiscounts =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_discounts);


        fabOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openMap();
            }
        });

        fabOpenDiscounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openDiscounts();
            }
        });

        imageView = (ImageView) root.findViewById(R.id.imageShopType);
        nameShopView = (TextView) root.findViewById(R.id.shop_name);
        workingHoursView = (TextView) root.findViewById(R.id.working_hours);
        shopStateView = (TextView) root.findViewById(R.id.shop_state);
        typeShop = (TextView) root.findViewById(R.id.shop_type);


        return root;
    }

    @Override
    public void showShopDetails(Shop shop) {
        switch (shop.getType()) {
            case 1:
                imageView.setImageResource(R.drawable.magnit_home);
                typeShop.setText(R.string.magnit_home);
                break;
            case 2:
                imageView.setImageResource(R.drawable.giper_magnit);
                typeShop.setText(R.string.magnit_giper);
                break;
            case 3:
                imageView.setImageResource(R.drawable.magnit_kosmetic);
                typeShop.setText(R.string.magnit_kosmetic);
                break;
            default:
                imageView.setImageResource(R.drawable.magnit_default);
                typeShop.setText(R.string.magnit_default);
                break;
        }
        nameShopView.setText(shop.getName());
        workingHoursView.setText(new StringBuilder().
                append(shop.getOpening()).
                append(" - ").
                append(shop.getClosing()).toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            Calendar dateOpen = Calendar.getInstance();
            dateOpen.setTime(dateFormat.parse(shop.getOpening()));
            Calendar dateClose = Calendar.getInstance();
            dateClose.setTime(dateFormat.parse(shop.getClosing()));
            Calendar currentDate = Calendar.getInstance();
            if (WorkWithTime.getTrueIfSetTimeIsBetween(currentDate, dateOpen, dateClose)) {
                shopStateView.setTextColor(Color.GREEN);
                shopStateView.setText(R.string.open);
            } else {

                shopStateView.setTextColor(Color.RED);
                shopStateView.setText(R.string.close);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            log.logp(Level.INFO, "ShopsAdapter", "onBindViewHolder", "Ошибка при парсинге даты, скорее всего пустая дата");
        }
    }

    @Override
    public void showDiscountsDetailsUi(Shop shop) {
        Intent intent = new Intent(getContext(), DiscountsInSelectedShopActivity.class);
        intent.putExtra(DiscountsInSelectedShopActivity.SHOP_NAME, shop.getName());
        intent.putExtra(DiscountsInSelectedShopActivity.SHOP_ID, shop.getId());
        startActivity(intent);
    }

    @Override
    public void showShopOnMap(Shop shop) {
        Intent intent = new Intent(getContext(), MapsShopActivity.class);
        intent.putExtra(MapsShopActivity.SHOP_LONGITUDE, shop.getLng());
        intent.putExtra(MapsShopActivity.SHOP_LATITUDE, shop.getLat());
        intent.putExtra(MapsShopActivity.SHOP_NAME, shop.getName());
        startActivity(intent);
    }
}
