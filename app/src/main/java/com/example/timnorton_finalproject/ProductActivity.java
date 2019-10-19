package com.example.timnorton_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProductList = new ArrayList<>();

        ArrayList<String> details = new ArrayList<>();
        details.add("Cream");
        details.add("2% milk");
        details.add("Sugar");
        details.add("Sweetener");

        mProductList.add(new Product(1, "Original Blend", "Our best blend coffee", R.drawable.original_blend, 1.76, details));
        mProductList.add(new Product(2, "Dark Roast", "Our best Dark coffee", R.drawable.dark_roast, 1.76, details));

        ArrayList<String> details2 = new ArrayList<>();
        details2.add("Egg");
        details2.add("Sausage");
        details2.add("Bread");
        details2.add("Cheese");

        mProductList.add(new Product(3, "Sausage Breakfast Sandwich", "A Tims breakfast classic", R.drawable.breakfast_sandwich, 3.69, details2));

        ArrayList<String> details3 = new ArrayList<>();
        details3.add("Tea Leaf");
        details3.add("Milk");
        details3.add("Cream");
        details3.add("Sugar");

        mProductList.add(new Product(4, "Steeped Tea", "Our speciality tea", R.drawable.steeped_tea, 1.76, details3));

        ArrayList<String> details4 = new ArrayList<>();
        details4.add("Milk");
        details4.add("Cream");
        details4.add("Sugar");

        mProductList.add(new Product(5, "French Vanilla", "Our Unique blend latte", R.drawable.french_vanilla, 2.29, details4));

        ArrayList<String> details5 = new ArrayList<>();
        details5.add("Chicken");
        details5.add("Cheese");
        details5.add("Egg");
        details5.add("Bacon");

        mProductList.add(new Product(6, "Chicken Wrap", "Our best Grilled Chicken Wrap", R.drawable.chicken_wrap, 3.19, details5));

        mAdapter = new ProductList(mProductList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void showProductDetails(int selectedProduct) {
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("products", mProductList);
        intent.putExtra("selectedProduct", selectedProduct);
        startActivity(intent);
    }
}
