package com.example.pixxa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixxa.adapter.OrderAdapter;
import com.example.pixxa.database.DBHelper;

public class OrdersFragment extends Fragment {

    RecyclerView recyclerView;
    DBHelper db;
    OrderAdapter adapter;

    public OrdersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.recyclerOrders);

        db = new DBHelper(requireContext());

        recyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );

        adapter = new OrderAdapter(
                requireContext(),
                db.getOrders()
        );

        recyclerView.setAdapter(adapter);

        return view;
    }

}