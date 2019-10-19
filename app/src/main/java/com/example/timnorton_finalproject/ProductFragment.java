package com.example.timnorton_finalproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProductFragment extends Fragment implements View.OnClickListener {

    private Product mProduct;
    private ImageView mImgProduct;
    private TextView mTxtName;
    private TextView mTxtDesc;
    private ListView mListDetails;
    private TextView mTxtPrice;
    private EditText mTxtQty;
    private Button mBtnOrder;
    private OnClickOrderListener mOnClickOrderListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnClickOrderListener)
            mOnClickOrderListener = (OnClickOrderListener)context;
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof OnClickOrderListener) {
            mOnClickOrderListener = (OnClickOrderListener)activity;
        }
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        mImgProduct = view.findViewById(R.id.imgProduct);
        mTxtName = view.findViewById(R.id.txtName);
        mTxtDesc = view.findViewById(R.id.txtDesc);
        mListDetails = view.findViewById(R.id.listDetails);
        mTxtPrice = view.findViewById(R.id.txtPrice);
        mTxtQty = view.findViewById(R.id.txtQty);
        mBtnOrder = view.findViewById(R.id.btnOrder);

        Bundle args = getArguments();
        mProduct = args.getParcelable("product");

        mImgProduct.setImageResource(mProduct.getImgResource());
        mTxtName.setText(mProduct.getName());
        mTxtDesc.setText(mProduct.getDesc());

        ArrayAdapter<String> detailsListAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.list_item,
                mProduct.getDetails()
        );

        mListDetails.setAdapter(detailsListAdapter);
        setListViewHeightBasedOnChildren(mListDetails);

        mTxtPrice.setText("$" + mProduct.getPrice());
        mBtnOrder.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Integer qty =  Integer.parseInt(mTxtQty.getText().toString().trim());
        if(!TextUtils.isEmpty(qty.toString()))
            mOnClickOrderListener.onOrder(mProduct, qty);
        else
            Toast.makeText(getContext(), "Quantity is Required!", Toast.LENGTH_SHORT).show();
    }

    void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
