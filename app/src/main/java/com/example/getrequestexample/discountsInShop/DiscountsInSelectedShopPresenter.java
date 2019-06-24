package com.example.getrequestexample.discountsInShop;

import android.graphics.Bitmap;

import com.example.getrequestexample.data.Discount;

import java.util.List;

public class DiscountsInSelectedShopPresenter implements DiscountsInSelectedShopContract.Presenter {

    DiscountsInSelectedShopContract.View mView;
    DiscountsInSelectedShopRepository mRepository;

    int mShopID;
    public DiscountsInSelectedShopPresenter(DiscountsInSelectedShopContract.View view,
                                            DiscountsInSelectedShopRepository repository,int shopId) {
        mView=view;
        mRepository=repository;
        mShopID=shopId;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        loadDiscounts();
    }

    private void loadDiscounts() {

        mRepository.getRemoteDiscounts(mShopID,new RemoteDiscountsCallBack.GetRemoteDiscountsCallback() {
            @Override
            public void onDiscountsLoaded(List<Discount> discounts) {
                if(!mView.isActive()){
                    return;
                }
                loadImagesDiscounts(discounts);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void loadImagesDiscounts(List<Discount> discounts){

        for (final Discount discount:discounts) {
            mRepository.getImageDiscount(discount.getImage(), new RemoteDiscountsCallBack.RemoteImagesDiscountsCallBack() {
                @Override
                public void onImagesDiscountsLoaded(Bitmap bitmap) {
                    discount.setImageDiscount(bitmap);
                }

                @Override
                public void onDataNotAvailable() {

                }
            });

        }
        ProcessDiscounts(discounts);
    }
    private void ProcessDiscounts(List<Discount> discounts) {
        mView.showDiscounts(discounts);
    }
}
