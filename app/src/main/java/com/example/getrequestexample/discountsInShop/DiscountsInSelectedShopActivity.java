package com.example.getrequestexample.discountsInShop;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.getrequestexample.R;
import com.example.getrequestexample.utils.ActivityUtils;

public class DiscountsInSelectedShopActivity extends AppCompatActivity {

    DiscountsInSelectedShopPresenter mPresenter;

    public static final String SHOP_ID="shop_id";
    public static final String SHOP_NAME="shop_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounts_in_selected_shop);
        int shopID= getIntent().getIntExtra(SHOP_ID,0);
        String nameShop= getIntent().getStringExtra(SHOP_NAME);
        @SuppressLint("StringFormatMatches")
        String  title =getString(R.string.discount, nameShop);
        setTitle(title);

        DiscountsInSelectedShopFragment discountsInSelectedShopFragment=
                (DiscountsInSelectedShopFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (discountsInSelectedShopFragment == null) {
            // Create the fragment
            discountsInSelectedShopFragment = DiscountsInSelectedShopFragment.Instance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), discountsInSelectedShopFragment, R.id.contentFrame);
        }

        mPresenter=new DiscountsInSelectedShopPresenter(discountsInSelectedShopFragment,
                DiscountsInSelectedShopRepository.getInstance(),shopID);

    }
}
