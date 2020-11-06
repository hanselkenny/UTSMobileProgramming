package com.timotiushanselkenny.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timotiushanselkenny.uts.adapter.Drink;
import com.timotiushanselkenny.uts.adapter.DrinkListAdapter;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity implements DrinkListAdapter.ItemClickListener {
    ArrayList<Drink> drinks;
    private RecyclerView recyclerView;
    private DrinkListAdapter drinkListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        recyclerView = this.findViewById(R.id.drink_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        drinks = Drink.createDrinkList(200);
        // Create adapter passing in the sample user data
        drinkListAdapter = new DrinkListAdapter(this, drinks);
        drinkListAdapter.setClickListener(this);
        recyclerView.setAdapter(drinkListAdapter);
    }
    public void onItemClick(View view, int position) {
        Drink drink = drinkListAdapter.getItem(position);
        Intent i_1 = new Intent(this,OrderActivity.class);
        i_1.putExtra("name",drink.getName().toString());
        i_1.putExtra("price",drink.getPrice().toString());
        startActivity(i_1);
    }
}