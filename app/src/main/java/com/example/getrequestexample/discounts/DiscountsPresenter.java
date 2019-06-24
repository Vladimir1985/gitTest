package com.example.getrequestexample.discounts;

import com.example.getrequestexample.data.Shop;

public class DiscountsPresenter implements DiscountsContract.Presenter {

    Shop mShop;
    DiscountsFragment mView;

    public DiscountsPresenter(Shop shop, DiscountsFragment view) {
        mShop = shop;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadShop();
    }

    private void loadShop() {
        mView.showShopDetails(mShop);
    }


    @Override
    public void openMap() {
        mView.showShopOnMap(mShop);
    }

    @Override
    public void openDiscounts() {
        mView.showDiscountsDetailsUi(mShop);
    }


}
