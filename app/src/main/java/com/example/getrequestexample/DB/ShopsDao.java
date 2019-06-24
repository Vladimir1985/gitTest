package com.example.getrequestexample.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.getrequestexample.data.Shop;

import java.util.List;

@Dao
public interface ShopsDao {

    @Query("SELECT * FROM shops")
    List<Shop> getShops();

    @Query("SELECT * FROM shops WHERE entryid = :shopsId")
    Shop getShopById(long shopsId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertShop(List<Shop> shops);

    @Update
    int updateShops(Shop shop);

    @Query("DELETE FROM shops WHERE entryid = :shopsId")
    int deleteShopsById(String shopsId);

    @Query("DELETE FROM shops")
    void deleteAllShops();
}
