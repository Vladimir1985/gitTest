package com.example.getrequestexample.discountsInShop;

import com.example.getrequestexample.BasePresenter;
import com.example.getrequestexample.BaseView;
import com.example.getrequestexample.data.Discount;

import java.util.List;

public interface DiscountsInSelectedShopContract {
    interface View extends BaseView<Presenter> {
        public void showDiscounts(List<Discount> shops);
        public boolean isActive();

    }

    interface Presenter extends BasePresenter {

    }
}
