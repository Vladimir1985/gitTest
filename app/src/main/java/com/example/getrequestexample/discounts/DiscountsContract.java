package com.example.getrequestexample.discounts;

import com.example.getrequestexample.BasePresenter;
import com.example.getrequestexample.BaseView;
import com.example.getrequestexample.data.Shop;

public interface DiscountsContract {
    interface View extends BaseView<Presenter> {
        void showShopDetails(Shop shop);

        void showDiscountsDetailsUi(Shop shop);

        void showShopOnMap(Shop shop);
    }

    interface Presenter extends BasePresenter {
        public void openMap();

        public void openDiscounts();
    }
}
