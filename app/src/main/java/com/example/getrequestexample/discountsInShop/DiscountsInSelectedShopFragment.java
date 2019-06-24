package com.example.getrequestexample.discountsInShop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.getrequestexample.R;
import com.example.getrequestexample.data.Discount;
import com.example.getrequestexample.discounts.DiscountsAdapter;

import java.util.ArrayList;
import java.util.List;

public class DiscountsInSelectedShopFragment extends Fragment implements DiscountsInSelectedShopContract.View {

    DiscountsInSelectedShopContract.Presenter mPresenter;
    DiscountsAdapter mDiscountsListAdapter;

    public static DiscountsInSelectedShopFragment Instance() {
        return new DiscountsInSelectedShopFragment();
    }

    @Override
    public void setPresenter(DiscountsInSelectedShopContract.Presenter presenter) {
        mPresenter = presenter;
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
        mDiscountsListAdapter = new DiscountsAdapter(getActivity(), new ArrayList<Discount>(0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.discounts_fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.discounts_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // устанавливаем для списка адаптер
        recyclerView.setAdapter(mDiscountsListAdapter);


        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void showDiscounts(List<Discount> discounts) {
        mDiscountsListAdapter.replaceDidcounts(discounts);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


}
