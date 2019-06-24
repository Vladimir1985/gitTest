package com.example.getrequestexample;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.getrequestexample.data.DataSources;
import com.example.getrequestexample.shops.ShopsFragment;
import com.example.getrequestexample.shops.ShopsPresenter;
import com.example.getrequestexample.shops.ShopsRepository;
import com.example.getrequestexample.utils.ActivityUtils;


public class MainActivity extends AppCompatActivity {

    ShopsPresenter mShopsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        } else {
            init();
        }

    }

    private Location getCurrentLocation() {
        Location location = null;

        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        String mprovider = locationManager.getBestProvider(criteria, false);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
        location = locationManager.getLastKnownLocation(mprovider);

        return location;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    init();
                } else {
                    Toast toast = Toast.makeText(this, "Ошибка при получении разрешения",
                            Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
    }

    private void init() {
        //Создание фрагмента
        ShopsFragment shopsFragment =
                (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (shopsFragment == null) {
            shopsFragment = ShopsFragment.Instance();
            //Моя утилита для добавления фрагмента в активити
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), shopsFragment, R.id.contentFrame);
        }


        //Считываем дату последней успешной загрузки
        SharedPreferences sPref = getSharedPreferences(DataSources.MY_PREFERENCES_FILENAME, MODE_PRIVATE);
        String date_load = sPref.getString(DataSources.DATE_LOAD_PREFERENCES, null);

        //Это для отладки, раскоментить и получается что база грузится в первый раз и потому из сети
        //date_load = null;
        mShopsPresenter = new ShopsPresenter(shopsFragment, ShopsRepository.getInstance(date_load,
                getCurrentLocation()), getApplicationContext());
    }

}



