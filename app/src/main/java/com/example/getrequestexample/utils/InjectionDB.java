package com.example.getrequestexample.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.getrequestexample.DB.ShopDatabase;
import com.example.getrequestexample.DB.ShopsLocalDataSources;

// Это мое обычное решение. В этом проекте всего одна таблица и с довольно простой задачей
// Но если таблиц много то это неплохой вариант, я обычно делаю такой класс который возвращает классы управления
// Определенными таблицами (ShopsLocalDataSources - это в данном случае класс который управляет всеми действиями
// с нашей таблицей магазинов)

public class InjectionDB {

    public static ShopsLocalDataSources getShopsLocalDataSources(@NonNull Context context){
        ShopDatabase database = null;
    if(context!=null) {
        database = ShopDatabase.getInstance(context);
    }
    return ShopsLocalDataSources.getInstance(new AppExecutors(),database.shopsDao());
    }
}
