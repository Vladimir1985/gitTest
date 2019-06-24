package com.example.getrequestexample.discounts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.getrequestexample.R;
import com.example.getrequestexample.data.Shop;
import com.example.getrequestexample.utils.ActivityUtils;

public class DiscountsActivity extends AppCompatActivity {

    public static final String DETAIL_SHOP = "DETAIL_SHOP";
    Shop mShop;
    DiscountsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounts);

        mShop = (Shop) getIntent().getSerializableExtra(DETAIL_SHOP);
        @SuppressLint("StringFormatMatches")
        String title = getString(R.string.discount_activity, mShop.getName());
        setTitle(title);

        DiscountsFragment discountsFragment = (DiscountsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (discountsFragment == null) {
            discountsFragment = DiscountsFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    discountsFragment, R.id.contentFrame);
        }

        mPresenter = new DiscountsPresenter(mShop, discountsFragment);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
