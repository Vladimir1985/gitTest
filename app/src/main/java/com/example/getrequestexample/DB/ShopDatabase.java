package com.example.getrequestexample.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.getrequestexample.data.Shop;

@Database(entities = {Shop.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {
    private static ShopDatabase INSTANCE;

    public abstract ShopsDao shopsDao();

    private static final Object sLock = new Object();

    public static ShopDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ShopDatabase.class, "Shops.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}