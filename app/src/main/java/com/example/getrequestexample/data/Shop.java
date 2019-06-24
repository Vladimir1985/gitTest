package com.example.getrequestexample.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

@Entity(tableName = "shops")
public class Shop implements Comparable<Shop>, Serializable {

    public int getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(int KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    //Идентификатор магазина
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "entryid")
    private int KEY_ID;

    @Expose
    @ColumnInfo(name = "id")
    private int id;
    //Тип магазина
    @Expose
    @ColumnInfo(name = "type")
    private int type;
    //Название магазина
    @Expose
    @ColumnInfo(name = "name")
    private String name;
    //Код магазина
    @Expose
    @ColumnInfo(name = "code")
    private String code;
    //Адрес магазина
    @Expose
    @ColumnInfo(name = "address")
    private String address;
    //Долгота
    @Expose
    @ColumnInfo(name = "lng")
    private double lng;
    //Широта
    @Expose
    @ColumnInfo(name = "lat")
    private double lat;
    //Время открытия
    @Expose
    @ColumnInfo(name = "opening")
    private String opening;
    //Время закрытия
    @Expose
    @ColumnInfo(name = "closing")
    private String closing;
    //Прием пластиковых карт
    @Expose
    @ColumnInfo(name = "plastic")
    private Boolean plastic;
    //тип изменения
    @Expose
    @ColumnInfo(name = "modification")
    private String modification;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    //Расстояние до этого магазина от пользователя\
    @ColumnInfo(name = "distance")
    private Integer distance;


    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public Boolean isPlastic() {
        return plastic;
    }

    public void setPlastic(Boolean plastic) {
        this.plastic = plastic;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }


    @Override
    public int compareTo(Shop shop) {
        return this.getDistance().compareTo(shop.getDistance());
    }
}
