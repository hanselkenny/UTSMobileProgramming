package com.timotiushanselkenny.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.timotiushanselkenny.uts.database.DatabaseHandler;
import com.timotiushanselkenny.uts.database.DrinkTransaction;

public class OrderActivity extends AppCompatActivity {
    TextView Quantity;
    TextView Name;
    TextView Price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        setContentView(R.layout.activity_order);
        TextView names = (TextView)findViewById(R.id.namaDrink);
        names.setText(name);
        TextView prices = (TextView)findViewById(R.id.hargaDrink);
        prices.setText(price);

    }
    public void onClickSaveOrder(View view) {
        saveOrder();
    }
    private void saveOrder() {

        TextView Quantity = (TextView) findViewById(R.id.editQuantity);
        TextView Name = (TextView) findViewById(R.id.namaDrink);
        TextView Price = (TextView) findViewById(R.id.hargaDrink);
        DrinkTransaction drinkTransaction = new DrinkTransaction(Name.getText().toString(),Integer.parseInt(Price.getText().toString()),Integer.parseInt(Quantity.getText().toString()));
        DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);
        try {
            databaseHelper.addRecord(drinkTransaction);
        }catch (Exception e)
        {
            Log.e("exception", "Error while trying to add transaction to database");
        }
        Intent i_1 = new Intent(this, DrinkActivity.class);
        startActivity(i_1);
    }
}