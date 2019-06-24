package com.example.getrequestexample.shops;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.getrequestexample.data.Shop;
import com.example.getrequestexample.utils.DistanceCalculator;
import com.example.getrequestexample.web.App;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopsRepository {

    private static ShopsRepository INSTANCE;
    private Location location;

    String date_last_modified;

    private ShopsRepository(String date_load, Location location) {
        date_last_modified = date_load;
        this.location = location;
    }

    ;

    public Location getLocation() {
        return location;
    }

    public static ShopsRepository getInstance(String date_load, Location location) {
        if (INSTANCE == null) {
            INSTANCE = new ShopsRepository(date_load, location);
        }
        return INSTANCE;
    }

    public void getRemoteShops(@NonNull final RemoteShopsCallBack.GetRemoteShopsCallback callback) {

        final List<Shop> mShop = new ArrayList<Shop>();
        final List<Shop> finalShops = new ArrayList<Shop>();
        App.getShopsApi().getData(date_last_modified).enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                mShop.addAll(response.body());
                finalShops.addAll(SetDistanceData());
                Collections.sort(finalShops);
                callback.onShopsLoaded(finalShops);
                Log.i("RESPONCE REMOTE LOAD SHOPS= ", "TRUE");
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Log.i("FAILURE REMOTE LOAD SHOPS= ", "TRUE");
                callback.onDataNotAvailable();int i=3;
            }

            private List<Shop> SetDistanceData() {

                List<Shop> shops = new ArrayList<>();
                for (int i = 0; i < mShop.size(); i++) {
                    if (mShop.get(i).getModification().equals("deleted") || mShop.get(i).getOpening() == null ||
                            mShop.get(i).getClosing() == null) {
                        continue;
                    }
                    double distance = DistanceCalculator.getDistance(location.getLatitude(), location.getLongitude(),
                            mShop.get(i).getLat(), mShop.get(i).getLng());
                    mShop.get(i).setDistance((int) distance);
                    shops.add(mShop.get(i));
                }
                return shops;
            }
        });

    }


}
