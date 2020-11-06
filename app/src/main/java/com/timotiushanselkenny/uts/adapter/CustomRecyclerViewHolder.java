package com.timotiushanselkenny.uts.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timotiushanselkenny.uts.R;


public class CustomRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView viewPrice;
    private TextView viewName;
    public CustomRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        viewPrice = itemView.findViewById(R.id.priceDrink);
        viewName = itemView.findViewById(R.id.nameDrink);
    }

    public TextView getViewPrice(){
        return viewPrice;
    }
    public TextView getViewName(){
        return viewName;
    }
}