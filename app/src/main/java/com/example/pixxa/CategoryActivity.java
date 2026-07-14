package com.example.pixxa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixxa.adapter.CategoryAdapter;
import com.example.pixxa.model.Pizza;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView categoryTitle;

    ArrayList<Pizza> pizzaList;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.recyclerCategory);
        categoryTitle = findViewById(R.id.categoryTitle);

        String category = getIntent().getStringExtra("category");

        categoryTitle.setText(category);

        pizzaList = new ArrayList<>();

        if(category.equals("Pizza")){

            pizzaList.add(new Pizza(R.drawable.farmhouse,"Margherita",199));
            pizzaList.add(new Pizza(R.drawable.pepperoni,"Pepperoni",299));
            pizzaList.add(new Pizza(R.drawable.farmhouse,"Farmhouse",249));

        }

        else if(category.equals("Drinks")){

            pizzaList.add(new Pizza(R.drawable.coke,"Coca Cola",60));
            pizzaList.add(new Pizza(R.drawable.sprite,"Sprite",60));
            pizzaList.add(new Pizza(R.drawable.fanta,"Fanta",60));

        }

        else if(category.equals("Desserts")){

            pizzaList.add(new Pizza(R.drawable.cake,"Chocolate Cake",120));
            pizzaList.add(new Pizza(R.drawable.donuts,"Donut",80));

        }

        adapter = new CategoryAdapter(this, pizzaList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}