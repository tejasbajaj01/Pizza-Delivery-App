package com.example.pixxa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixxa.adapter.PizzaAdapter;
import com.example.pixxa.model.Pizza;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Pizza> pizzaList;
    PizzaAdapter adapter;
    EditText searchBox;
    TextView txtAddress;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerPizza);
        searchBox = view.findViewById(R.id.searchBox);
        txtAddress = view.findViewById(R.id.txtAddress);

        pizzaList = new ArrayList<>();

        pizzaList.add(new Pizza(
                R.drawable.farmhouse,
                "Margherita",
                199));

        pizzaList.add(new Pizza(
                R.drawable.pepperoni,
                "Pepperoni",
                299));

        pizzaList.add(new Pizza(
                R.drawable.farmhouse,
                "Farmhouse",
                249));

        adapter = new PizzaAdapter(requireContext(), pizzaList);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        searchBox.setFocusable(false);
        searchBox.setCursorVisible(false);
        searchBox.setClickable(true);

        searchBox.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, new SearchFragment())
                    .addToBackStack(null)
                    .commit();

        });
        SharedPreferences sp = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String address = sp.getString("address", "");

        if (address.isEmpty()) {
            txtAddress.setText("Tap to add address");
        } else {
            txtAddress.setText(address);
        }
        txtAddress.setOnClickListener(v -> {

            Intent intent = new Intent(requireContext(), AddressActivity.class);
            startActivity(intent);

        });


        view.findViewById(R.id.pizzaCategory).setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CategoryActivity.class);
            intent.putExtra("category", "Pizza");
            startActivity(intent);
        });

        view.findViewById(R.id.drinkCategory).setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CategoryActivity.class);
            intent.putExtra("category", "Drinks");
            startActivity(intent);
        });

        view.findViewById(R.id.dessertCategory).setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CategoryActivity.class);
            intent.putExtra("category", "Desserts");
            startActivity(intent);
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sp = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String address = sp.getString("address", "");

        if (address.isEmpty()) {
            txtAddress.setText("Tap to add address");
        } else {
            txtAddress.setText(address);
        }
    }
}