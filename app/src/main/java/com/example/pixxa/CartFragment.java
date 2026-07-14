package com.example.pixxa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixxa.adapter.CartAdapter;
import com.example.pixxa.database.DBHelper;
import com.example.pixxa.model.Cart;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    RecyclerView cartRecycler;
    TextView totalPrice;
    Button placeOrderBtn;

    DBHelper dbHelper;
    ArrayList<Cart> cartList;

    public CartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecycler = view.findViewById(R.id.cartRecycler);
        totalPrice = view.findViewById(R.id.totalPrice);
        placeOrderBtn = view.findViewById(R.id.placeOrderBtn);

        dbHelper = new DBHelper(requireContext());

        cartList = dbHelper.getCartItems();

        CartAdapter adapter = new CartAdapter(requireContext(), cartList);

        cartRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        cartRecycler.setAdapter(adapter);

        int total = 0;

        for (Cart c : cartList) {
            total += c.getPrice() * c.getQuantity();
        }

        totalPrice.setText("Total: ₹" + total);

        placeOrderBtn.setOnClickListener(v -> {

            SharedPreferences sp = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String address = sp.getString("address", "No Address Set");
            String status = "Preparing";
            String time = String.valueOf(System.currentTimeMillis());

            dbHelper.placeOrder(address, status, time);

            Toast.makeText(
                    requireContext(),
                    "Order Placed Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            cartList.clear();
            adapter.notifyDataSetChanged();

            totalPrice.setText("Total: ₹0");
        });

        return view;
    }
}