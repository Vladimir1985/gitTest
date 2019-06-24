package com.example.getrequestexample.discountsInShop;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.getrequestexample.data.Discount;

import java.util.List;

public interface RemoteDiscountsCallBack {

    interface GetRemoteDiscountsCallback {

        void onDiscountsLoaded(List<Discount> discounts);

        void onDataNotAvailable();
    }

  interface RemoteImagesDiscountsCallBack{

     void onImagesDiscountsLoaded(Bitmap bitmap);

     void onDataNotAvailable();
 }

    void getRemoteDiscounts(@NonNull int shopID,@NonNull GetRemoteDiscountsCallback callback);
    void getImageDiscount(String imageName,@NonNull RemoteImagesDiscountsCallBack callBack);


}
