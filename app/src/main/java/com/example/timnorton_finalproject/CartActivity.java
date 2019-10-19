package com.example.timnorton_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView t = findViewById(R.id.txtMsg);

        SharedPreferences mPrefs = getSharedPreferences("Cart", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString("cartItem", "");
        t.setText(json);
    }
}
