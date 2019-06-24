package com.example.getrequestexample.shops;

import com.example.getrequestexample.BasePresenter;
import com.example.getrequestexample.BaseView;
import com.example.getrequestexample.data.NearestShopArea;
import com.example.getrequestexample.data.Shop;

import java.util.List;

public interface ShopsContract {
    interface View extends BaseView<Presenter> {

        public void showShops(List<Shop> shops);

        public boolean isActive();

        public void showShopDetailUi(Shop shop);

        public void showNearestShopsOnMap(NearestShopArea nearestShopArea);

    }

    interface Presenter extends BasePresenter {
        public void openMap();

        public void openShopDetails(Shop clickedShop);
    }
}
