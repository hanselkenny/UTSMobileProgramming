package com.timotiushanselkenny.uts.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.timotiushanselkenny.uts.DrinkActivity;
import com.timotiushanselkenny.uts.OrderActivity;
import com.timotiushanselkenny.uts.PaymentActivity;
import com.timotiushanselkenny.uts.R;
import com.timotiushanselkenny.uts.adapter.Drink;
import com.timotiushanselkenny.uts.adapter.DrinkListAdapter;
import com.timotiushanselkenny.uts.adapter.TransactionListAdapter;
import com.timotiushanselkenny.uts.database.DatabaseHandler;
import com.timotiushanselkenny.uts.database.DrinkTransaction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment implements TransactionListAdapter.ItemClickListener{
    private RecyclerView recyclerView;
    private TransactionListAdapter drinkListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        recyclerView = root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        // Create adapter passing in the sample user data
        DatabaseHandler databaseHelper = DatabaseHandler.getInstance(getActivity());
        List<DrinkTransaction> drinkTransactions = new ArrayList<>();
        try {
            drinkTransactions = databaseHelper.getAllRecord();
        }catch (Exception e)
        {
            Log.e("exception", "Error while trying to add transaction to database");
        }
        drinkListAdapter = new TransactionListAdapter(getActivity(), drinkTransactions);
        recyclerView.setAdapter(drinkListAdapter);
        int totalPrice=0;
        for (DrinkTransaction drink :drinkTransactions) {
            totalPrice = totalPrice + Integer.parseInt(drink.getPrice()) * Integer.parseInt(drink.getQuantity());
        }
        TextView textView = root.findViewById(R.id.totalPrices);
        textView.setText(String.valueOf(totalPrice));
        return root;
    }

    public void onItemHapus(View view, int position) {
        DrinkTransaction drink = drinkListAdapter.getItem(position);
        DatabaseHandler databaseHelper = DatabaseHandler.getInstance(getActivity());
        try {
            databaseHelper.deleteModel(drink);
        }catch (Exception e)
        {
            Log.e("exception", "Error while trying to add transaction to database");
        }
        Snackbar.make(view, "Data berhasil di hapus", Snackbar.LENGTH_LONG).show();
    }

}