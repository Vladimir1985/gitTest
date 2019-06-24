package com.example.getrequestexample.shops;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.getrequestexample.R;
import com.example.getrequestexample.data.NearestShopArea;
import com.example.getrequestexample.data.Shop;
import com.example.getrequestexample.discounts.DiscountsActivity;
import com.example.getrequestexample.maps.MapsNearestShopsActivity;

import java.util.ArrayList;
import java.util.List;

public class ShopsFragment extends Fragment implements ShopsContract.View {

    ShopsContract.Presenter mPresenter;
    ShopsAdapter mShopsListAdapter;

    public static ShopsFragment Instance() {
        return new ShopsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // инициализируем адаптер
        mShopsListAdapter = new ShopsAdapter(getActivity(), new ArrayList<Shop>(0), mItemListener);
    }

    @Override
    public void setPresenter(ShopsContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shops_fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.shops_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // устанавливаем для списка адаптер
        recyclerView.setAdapter(mShopsListAdapter);

        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_open_map);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openMap();
            }
        });

        setHasOptionsMenu(true);

        return root;
    }


    @Override
    public void showShops(List<Shop> shops) {

        if (shops == null || shops.size() == 0) {
            Toast toast = Toast.makeText(getContext(), "Извините, данные сейчас недоступны, попробуйте позже.",
                    Toast.LENGTH_LONG);
            toast.show();
        } else {
            mShopsListAdapter.replaceShops(shops);
        }
        //Для отладки использую
        Toast toast = Toast.makeText(getContext(), "Количество записей = " + shops.size(),
                Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showShopDetailUi(Shop shop) {
        Intent intent = new Intent(getContext(), DiscountsActivity.class);
        intent.putExtra(DiscountsActivity.DETAIL_SHOP, shop);
        startActivity(intent);
    }

    @Override
    public void showNearestShopsOnMap(NearestShopArea nearestShopArea) {
        Intent intent = new Intent(getContext(), MapsNearestShopsActivity.class);
        intent.putExtra(MapsNearestShopsActivity.SHOPS_LATITUDE, nearestShopArea.getLtd());
        intent.putExtra(MapsNearestShopsActivity.SHOPS_LONGITUDE, nearestShopArea.getLng());
        intent.putExtra(MapsNearestShopsActivity.SHOPS_NAME, nearestShopArea.getName());
        intent.putExtra(MapsNearestShopsActivity.MY_LATITUDE, nearestShopArea.getMy_latitude());
        intent.putExtra(MapsNearestShopsActivity.MY_LONGITUDE, nearestShopArea.getMy_longitude());
        startActivity(intent);
    }


    ///////////////////// Listener Shops List
    ShopItemListener mItemListener = new ShopItemListener() {
        @Override
        public void onShopClick(Shop clickedShop) {
            mPresenter.openShopDetails(clickedShop);
        }

    };

    interface ShopItemListener {

        void onShopClick(Shop clickedShop);

    }
}