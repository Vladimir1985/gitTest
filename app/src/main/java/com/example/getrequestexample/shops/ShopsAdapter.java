package com.example.getrequestexample.shops;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.getrequestexample.R;
import com.example.getrequestexample.data.Shop;
import com.example.getrequestexample.utils.WorkWithTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//Адаптер для списка магазинов
class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Shop> shops;
    private ShopsFragment.ShopItemListener mShopListener;
    Logger log;

    ShopsAdapter(Context context, List<Shop> shops, ShopsFragment.ShopItemListener listener) {
        this.shops = shops;
        this.inflater = LayoutInflater.from(context);
        mShopListener = listener;
        log = Logger.getLogger(ShopsAdapter.class.getName());
    }

    @Override
    public ShopsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = inflater.inflate(R.layout.shops_fragment_list_item, parent, false);
        return new ViewHolder(view);
    }


    public Shop getItem(int i) {
        return shops.get(i);
    }

    @Override
    public void onBindViewHolder(ShopsAdapter.ViewHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.bind(shop);
        if (shop.getName() == null || shop.getName() == "")
            return;
        holder.nameShopView.setText(shop.getName());
        switch (shop.getType()) {
            case 1:
                holder.imageView.setImageResource(R.drawable.magnit_home);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.giper_magnit);
                break;
            case 3:
                holder.imageView.setImageResource(R.drawable.magnit_kosmetic);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.magnit_default);
                break;
        }

        holder.workingHoursView.setText(new StringBuilder().
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
                holder.shopStateView.setTextColor(Color.GREEN);
                holder.shopStateView.setText(R.string.open);
            } else {
                holder.shopStateView.setTextColor(Color.RED);
                holder.shopStateView.setText(R.string.close);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            log.logp(Level.INFO, "ShopsAdapter", "onBindViewHolder", "Ошибка при парсинге даты, скорее всего пустая дата");
        }
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public void replaceShops(List<Shop> shops) {
        setList(shops);
        notifyDataSetChanged();
    }

    private void setList(@NonNull List<Shop> shops) {
        this.shops = Objects.requireNonNull(shops, "shops in ShopsAdapter must not be null");

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameShopView, workingHoursView, shopStateView;
        final LinearLayout shortShopLayout;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageShopType);
            nameShopView = (TextView) view.findViewById(R.id.shop_name);
            workingHoursView = (TextView) view.findViewById(R.id.working_hours);
            shopStateView = (TextView) view.findViewById(R.id.shop_state);
            shortShopLayout = (LinearLayout) view.findViewById(R.id.shortShopLayout);
        }

        public void bind(final Shop shop) {

            shortShopLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mShopListener.onShopClick(shop);
                }
            });
        }

    }
}
