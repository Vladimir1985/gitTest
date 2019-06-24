package com.example.getrequestexample.DB;

import android.support.annotation.NonNull;

import com.example.getrequestexample.data.Shop;
import com.example.getrequestexample.utils.AppExecutors;

import java.util.List;


public class ShopsLocalDataSources implements LocalShopsCallBack {

    private static volatile ShopsLocalDataSources INSTANCE;
    AppExecutors mAppExecutors;
    ShopsDao mShopsDao;

    private ShopsLocalDataSources(@NonNull AppExecutors appExecutors,
                                  @NonNull ShopsDao shopsDao) {
        mAppExecutors = appExecutors;
        mShopsDao = shopsDao;
    }

    public static ShopsLocalDataSources getInstance(@NonNull AppExecutors appExecutors, @NonNull ShopsDao shopsDao) {
        if (INSTANCE == null) {
            synchronized (ShopsLocalDataSources.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShopsLocalDataSources(appExecutors, shopsDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getLocalShops(@NonNull final GetLocalShopsCallback callback) {
        mAppExecutors = new AppExecutors();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Shop> shops = mShopsDao.getShops();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (shops != null) {
                            callback.onShopsLoaded(shops);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveShopsJson(@NonNull List<Shop> shops) {
        mAppExecutors = new AppExecutors();
        final List<Shop> mShops = shops;
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mShopsDao.deleteAllShops();
                mShopsDao.insertShop(mShops);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

}
