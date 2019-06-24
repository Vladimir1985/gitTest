package com.example.getrequestexample.discounts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getrequestexample.R;
import com.example.getrequestexample.data.Discount;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class DiscountsAdapter extends RecyclerView.Adapter<DiscountsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Discount> discounts;
    Logger log;

    public DiscountsAdapter(Context context, List<Discount> discounts) {
        this.discounts = discounts;
        this.inflater = LayoutInflater.from(context);
        log = Logger.getLogger(DiscountsAdapter.class.getName());
    }

    @Override
    public DiscountsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = inflater.inflate(R.layout.discounts_fragment_list_item, parent, false);
        return new ViewHolder(view);
    }

    public Discount getItem(int i) {
        return discounts.get(i);
    }

    @Override
    public void onBindViewHolder(DiscountsAdapter.ViewHolder holder, int position) {
        Discount discount = discounts.get(position);

        holder.imageView.setImageBitmap(discount.getImageDiscount());
        holder.nameDiscountView.setText(discount.getName());
        holder.workingDaysView.setText(new StringBuilder().
                append(discount.getStartDate()).
                append(" - ").
                append(discount.getEndDate()).toString());

        holder.oldPriceView.setText(String.valueOf(discount.getOldPrice()) + "   ");
        holder.newPriceView.setText(String.valueOf(discount.getPrice()));
    }

    @Override
    public int getItemCount() {
        return discounts.size();
    }

    public void replaceDidcounts(List<Discount> discounts) {
        setList(discounts);
        notifyDataSetChanged();
    }

    private void setList(@NonNull List<Discount> discounts) {
        this.discounts = Objects.requireNonNull(discounts, "shops in ShopsAdapter must not be null");

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameDiscountView, workingDaysView, oldPriceView, newPriceView;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageDiscount);
            nameDiscountView = (TextView) view.findViewById(R.id.discount_name);
            workingDaysView = (TextView) view.findViewById(R.id.working_days);
            oldPriceView = (TextView) view.findViewById(R.id.old_price);
            newPriceView = (TextView) view.findViewById(R.id.new_price);

        }


    }
}
