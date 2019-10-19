package com.example.timnorton_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductList extends RecyclerView.Adapter<ProductList.ViewHolder> {

    private List<Product> mProducts;
    private Context context;

    public ProductList(List<Product> mProducts, Context context) {
        this.mProducts = mProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final int count = i;
        final Product selectedProduct = mProducts.get(i);

        viewHolder.tvName.setText(selectedProduct.getName());
        viewHolder.tvDesc.setText(selectedProduct.getDesc());
        viewHolder.imgProduct.setImageResource(selectedProduct.getImgResource());

        viewHolder.productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ProductActivity) context).showProductDetails(count);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDesc;
        ImageView imgProduct;
        LinearLayout productLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            productLayout = itemView.findViewById(R.id.productLayout);
        }
    }
}
