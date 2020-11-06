package com.timotiushanselkenny.uts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timotiushanselkenny.uts.R;
import com.timotiushanselkenny.uts.database.DrinkTransaction;

import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder> {

    private List<DrinkTransaction> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public TransactionListAdapter(Context context, List<DrinkTransaction> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.frame_orderview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myNameView.setText(mData.get(position).getName());
        holder.myPriceView.setText(mData.get(position).getPrice());
        holder.myQuantityView.setText(mData.get(position).getQuantity());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button hapus;
        TextView myPriceView;
        TextView myNameView;
        TextView myQuantityView;

        ViewHolder(View itemView) {
            super(itemView);
            hapus = itemView.findViewById(R.id.buttonRemoveOrder);
            myPriceView = itemView.findViewById(R.id.orderPriceDrink);
            myNameView = itemView.findViewById(R.id.orderNameDrink);
            myQuantityView = itemView.findViewById(R.id.orderQuantityDrink);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemHapus(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public DrinkTransaction getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemHapus(View view, int position);
    }
}