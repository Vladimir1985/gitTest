package com.example.getrequestexample.discountsInShop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.getrequestexample.data.DataSources;
import com.example.getrequestexample.data.Discount;

import com.example.getrequestexample.web.App;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountsInSelectedShopRepository implements RemoteDiscountsCallBack {

    private static DiscountsInSelectedShopRepository INSTANCE;
    private final String DISCOUNT_PARAMETR_PUBLICHER = "МПМ";



    public DiscountsInSelectedShopRepository() {

    }

    public static DiscountsInSelectedShopRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiscountsInSelectedShopRepository();
        }
        return INSTANCE;
    }

    @Override
    public void getRemoteDiscounts(@NonNull final int shopID, @NonNull final GetRemoteDiscountsCallback callback) {

        final List<Discount> mDiscounts = new ArrayList<>();
        App.getDiscountsApi().getData(shopID, DISCOUNT_PARAMETR_PUBLICHER).enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                mDiscounts.addAll(response.body());
                Log.i("RESPONCE REMOTE LOAD DISCOUNTS= ", "TRUE");
                callback.onDiscountsLoaded(mDiscounts);
            }

            @Override
            public void onFailure(Call<List<Discount>> call, Throwable t) {
                Log.i("FAILURE REMOTE LOAD DISCOUNTS= ", "TRUE");
                callback.onDataNotAvailable();
            }


        });
    }

    @Override
    public void getImageDiscount(String imageName, final RemoteImagesDiscountsCallBack callBack){

        final Bitmap[] image = new Bitmap[1];
        App.getDiscountsImagesApi().getData(DataSources.IMAGES_500_URL+imageName).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                image[0] = BitmapFactory. decodeStream(response.body().byteStream());
                Log.i("RESPONCE REMOTE IMAGE DISCOUNTS= ", "TRUE");
                callBack.onImagesDiscountsLoaded(image[0]);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("FAILURE REMOTE IMAGE DISCOUNTS= ", "TRUE");
            }
        });

    }
}
