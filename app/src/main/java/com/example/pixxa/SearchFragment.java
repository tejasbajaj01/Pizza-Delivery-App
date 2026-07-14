package com.example.pixxa;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixxa.adapter.CategoryAdapter;
import com.example.pixxa.adapter.PizzaAdapter;
import com.example.pixxa.model.Pizza;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    EditText searchBox;
    RecyclerView recyclerView;
    ArrayList<Pizza> list;
    CategoryAdapter adapter;
    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchBox = view.findViewById(R.id.searchBox);
        ImageView backBtn = view.findViewById(R.id.backBtn);
        recyclerView = view.findViewById(R.id.searchRecycler);

        list = new ArrayList<>();

        list.add(new Pizza(R.drawable.farmhouse, "Margherita", 199));
        list.add(new Pizza(R.drawable.pepperoni, "Pepperoni", 299));
        list.add(new Pizza(R.drawable.farmhouse, "Farmhouse", 249));
        list.add(new Pizza(R.drawable.coke, "Coke", 60));
        list.add(new Pizza(R.drawable.sprite, "Sprite", 60));
        list.add(new Pizza(R.drawable.fanta, "Fanta", 60));
        list.add(new Pizza(R.drawable.cake, "Cake", 120));
        list.add(new Pizza(R.drawable.donuts, "Donut", 80));

        adapter = new CategoryAdapter(requireContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        searchBox.requestFocus();

        searchBox.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        backBtn.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );

        return view;
    }
}