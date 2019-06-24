package com.example.getrequestexample.data;

import java.util.List;

public class NearestShopArea {

    String[] name;
    double[] ltd;
    double[] lng;

    public String[] getName() {
        return name;
    }

    public double[] getLtd() {
        return ltd;
    }

    public double[] getLng() {
        return lng;
    }

    public double getMy_longitude() {
        return my_longitude;
    }

    public double getMy_latitude() {
        return my_latitude;
    }

    double my_longitude, my_latitude;

    public NearestShopArea(List<Shop> shops, double latitude, double longitude) {
        this.my_latitude = latitude;
        this.my_longitude = longitude;
        SetValues(shops);
    }

    private void SetValues(List<Shop> shops) {
        name = new String[shops.size()];
        ltd = new double[shops.size()];
        lng = new double[shops.size()];
        for (int i = 0; i < shops.size(); i++) {
            name[i] = shops.get(i).getName();
            ltd[i] = shops.get(i).getLat();
            lng[i] = shops.get(i).getLng();
        }
    }


}
