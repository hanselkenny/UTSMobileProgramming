package com.timotiushanselkenny.uts.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timotiushanselkenny.uts.R;


public class TransactionViewHolder extends RecyclerView.ViewHolder {

    private TextView viewPrice;
    private TextView viewName;
    private TextView viewQuantity;
    public TransactionViewHolder(@NonNull View itemView) {
        super(itemView);
        viewPrice = itemView.findViewById(R.id.orderPriceDrink);
        viewName = itemView.findViewById(R.id.orderNameDrink);
        viewQuantity = itemView.findViewById(R.id.orderQuantityDrink);
    }

    public TextView getViewPrice(){
        return viewPrice;
    }
    public TextView getViewName(){
        return viewName;
    }
    public TextView getViewQuantity(){
        return viewQuantity;
    }
}