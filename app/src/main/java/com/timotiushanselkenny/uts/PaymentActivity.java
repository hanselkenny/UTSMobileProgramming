package com.timotiushanselkenny.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.timotiushanselkenny.uts.adapter.TransactionListAdapter;
import com.timotiushanselkenny.uts.database.DatabaseHandler;
import com.timotiushanselkenny.uts.database.DrinkTransaction;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TransactionListAdapter drinkListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        recyclerView = this.findViewById(R.id.order_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Create adapter passing in the sample user data
        DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);
        List<DrinkTransaction> drinkTransactions = new ArrayList<>();
        try {
            drinkTransactions = databaseHelper.getAllRecord();
        }catch (Exception e)
        {
            Log.e("exception", "Error while trying to add transaction to database");
        }
        drinkListAdapter = new TransactionListAdapter(this, drinkTransactions);
        recyclerView.setAdapter(drinkListAdapter);
        int totalPrice=0;
        for (DrinkTransaction drink :drinkTransactions) {
            totalPrice = totalPrice + Integer.parseInt(drink.getPrice()) * Integer.parseInt(drink.getQuantity());
        }
        final TextView textView = (TextView)findViewById(R.id.totalPricesPayment);
        textView.setText(String.valueOf(totalPrice));
    }
}