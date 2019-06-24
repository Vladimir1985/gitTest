package com.example.getrequestexample.shops;


import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.NonNull;

import com.example.getrequestexample.DB.LocalShopsCallBack;
import com.example.getrequestexample.DB.ShopsLocalDataSources;
import com.example.getrequestexample.data.DataSources;
import com.example.getrequestexample.data.NearestShopArea;
import com.example.getrequestexample.data.Shop;
import com.example.getrequestexample.utils.InjectionDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

import static android.content.Context.MODE_PRIVATE;

public class ShopsPresenter implements ShopsContract.Presenter {

    ShopsRepository mRepository;
    ShopsLocalDataSources mShopsLocalDataSources;
    ShopsContract.View mView;
    Logger log;
    Context context;
    List<Shop> mShops;
    final int CIRCLE_NEAREST_SHOPS = 2;

    public ShopsPresenter(@NonNull ShopsContract.View view, @NonNull ShopsRepository repository, Context context) {
        mView = Objects.requireNonNull(view, "mView in ShopsPresenter must not be null");
        mRepository = Objects.requireNonNull(repository, "mRepository in ShopsPresenter must not be null");
        mView.setPresenter(this);
        this.context = context;
        mShopsLocalDataSources = InjectionDB.getShopsLocalDataSources(this.context);

    }


    @Override
    public void start() {
        loadShopsRemote();
    }

    private void loadShopsRemote() {

        mRepository.getRemoteShops(new RemoteShopsCallBack.GetRemoteShopsCallback() {

            @Override
            public void onShopsLoaded(List<Shop> shops) {
                if (!mView.isActive()) {
                    return;
                }
                mShops = shops;
                if (mShops.size() != 1 && mShops.size() != 0) {
                    saveShopsLocal();
                } else {
                    loadShopsLocal();
                }

            }

            @Override
            public void onDataNotAvailable() {
                loadShopsLocal();
            }


        });
    }

    //Метод вызывается если удалось получить данные из сети, сохраняем результат в БД
    private void saveShopsLocal() {
        SimpleDateFormat formatDate = new SimpleDateFormat("E MMM dd y HH:mm:ss 'GMT'Z '(MSK)'",
                new Locale("en"));
        String successfulUploadDate = formatDate.format(new Date()).toString();
        //Вот здесь сохраняю в БД
        mShopsLocalDataSources.saveShopsJson(mShops);
        //Потом дату в свойства
        SharedPreferences sPref = context.getSharedPreferences(DataSources.MY_PREFERENCES_FILENAME,
                MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(DataSources.DATE_LOAD_PREFERENCES, successfulUploadDate);
        ed.commit();
        //И затем вывод магазинов на экран
        ProcessingShops(mShops);
    }

    //Метод вызывается если не удалось получить данные из сети, пытаемся загрузить из БД
    private void loadShopsLocal() {

        final List<Shop>[] mShops = new List[]{new ArrayList<Shop>()};
        mShopsLocalDataSources.getLocalShops(new LocalShopsCallBack.GetLocalShopsCallback() {
            @Override
            public void onShopsLoaded(List<Shop> shops) {

                mShops[0] = shops;
                ProcessingShops(mShops[0]);
            }

            @Override
            public void onDataNotAvailable() {
                ProcessingShops(mShops[0]);
            }
        });

    }

    private void ProcessingShops(List<Shop> shops) {
        mView.showShops(shops);
    }

    @NonNull
    List<Shop> GetNearestShop() {
        List<Shop> nearestShops = new ArrayList<>();
        for (Shop shop : mShops) {
            if (shop.getDistance() <= CIRCLE_NEAREST_SHOPS) {
                nearestShops.add(shop);
            } else {
                return nearestShops;
            }
        }
        return nearestShops;
    }

    @Override
    public void openMap() {
        Location location = mRepository.getLocation();
        NearestShopArea nearestShopArea = new NearestShopArea(GetNearestShop(), location.getLatitude(),
                location.getLongitude());
        mView.showNearestShopsOnMap(nearestShopArea);
    }

    @Override
    public void openShopDetails(Shop clickedShop) {

        mView.showShopDetailUi(clickedShop);

    }
}
