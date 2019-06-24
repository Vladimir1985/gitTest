package com.example.getrequestexample.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.getrequestexample.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsShopActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static final String SHOP_LONGITUDE = "shop_longitude";
    public static final String SHOP_LATITUDE = "shop_latitude";
    public static final String SHOP_NAME = "shop_name";
    private final float SHOP_MAP_ZOOM = 18.0f;

    double shop_longitude, shop_latitude;
    String shop_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_shop);

        shop_longitude = getIntent().getDoubleExtra(SHOP_LONGITUDE, 0);
        shop_latitude = getIntent().getDoubleExtra(SHOP_LATITUDE, 0);
        shop_name = getIntent().getStringExtra(SHOP_NAME);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng shop = new LatLng(shop_latitude, shop_longitude);
        mMap.addMarker(new MarkerOptions().position(shop).title(shop_name));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(shop, SHOP_MAP_ZOOM));
    }
}
