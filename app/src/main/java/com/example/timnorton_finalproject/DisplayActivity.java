package com.example.timnorton_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity implements OnClickOrderListener{

    private ViewPager mFragmentContainer;
    private FragmentAdapter mFragmentAdapter;
    private ArrayList<Product> mProductList;
    private int selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mProductList = getIntent().getParcelableArrayListExtra("products");
        selectedProduct = getIntent().getIntExtra("selectedProduct", 0);

        mFragmentContainer = findViewById(R.id.fragmentContainer);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        for(Product product : mProductList) {
            ProductFragment productFragment = new ProductFragment();

            Bundle args = new Bundle();
            args.putParcelable("product", product);
            productFragment.setArguments(args);

            mFragmentAdapter.addFragment(productFragment);
        }

        mFragmentContainer.setAdapter(mFragmentAdapter);

        setupViewPager(selectedProduct);
    }

    public void setupViewPager(int fragmentNumber) { mFragmentContainer.setCurrentItem(fragmentNumber); }

    public void onOrder(Product selectedProduct, int qty) {
        Intent intent = new Intent(this, CartActivity.class);
        SharedPreferences preferences = getSharedPreferences("Cart", Context.MODE_PRIVATE);
        Cart cart = new Cart(selectedProduct.get_id(), qty);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cart);
        editor.putString("cartItem", json);
        editor.commit();

        intent.putExtra("Products", mProductList);
        startActivity(intent);
    }
}
