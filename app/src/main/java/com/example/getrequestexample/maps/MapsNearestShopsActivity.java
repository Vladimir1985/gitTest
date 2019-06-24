package com.example.getrequestexample.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.getrequestexample.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsNearestShopsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static final String SHOPS_LONGITUDE = "shops_longitude";
    public static final String SHOPS_LATITUDE = "shops_latitude";
    public static final String SHOPS_NAME = "shops_name";
    public static final String MY_LONGITUDE = "my_longitude";
    public static final String MY_LATITUDE = "my_latitude";
    private final float SHOP_MAP_ZOOM = 14.0f;
    double[] shops_longitude, shops_latitude;
    String[] shops_name;
    double my_longitude, my_latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_nearest_shops);

        shops_longitude = getIntent().getDoubleArrayExtra(SHOPS_LONGITUDE);
        shops_latitude = getIntent().getDoubleArrayExtra(SHOPS_LATITUDE);
        shops_name = getIntent().getStringArrayExtra(SHOPS_NAME);
        my_latitude = getIntent().getDoubleExtra(MY_LATITUDE, 0);
        my_longitude = getIntent().getDoubleExtra(MY_LONGITUDE, 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<LatLng> latLngList = new ArrayList<>();
        List<MarkerOptions> markerOptionsList = new ArrayList<>();

        LatLng myLatLng = new LatLng(my_latitude, my_longitude);
        mMap.addMarker((new MarkerOptions().position(myLatLng).title("You here").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))));


        for (int i = 0; i < shops_name.length; i++) {
            LatLng LatLng = new LatLng(shops_latitude[i], shops_longitude[i]);
            mMap.addMarker((new MarkerOptions().position(LatLng).title("Магнит " + shops_name[i]).icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_RED))));
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, SHOP_MAP_ZOOM));
    }
}
