package com.example.pixxa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixxa.R;
import com.example.pixxa.database.DBHelper;
import com.example.pixxa.model.Cart;
import com.example.pixxa.model.Pizza;

import java.util.ArrayList;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    Context context;
    ArrayList<Pizza> pizzaList;
    ArrayList<Pizza> fullList;

    public PizzaAdapter(Context context, ArrayList<Pizza> pizzaList) {

        this.context = context;

        this.pizzaList = new ArrayList<>();
        this.pizzaList.addAll(pizzaList);

        this.fullList = new ArrayList<>();
        this.fullList.addAll(pizzaList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.pizza_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Pizza pizza = pizzaList.get(position);

        holder.image.setImageResource(pizza.getImage());
        holder.name.setText(pizza.getName());
        holder.price.setText("₹" + pizza.getPrice());

        holder.addBtn.setOnClickListener(v -> {

            DBHelper dbHelper = new DBHelper(context);

            Cart cart = new Cart(
                    0,
                    pizza.getName(),
                    pizza.getPrice(),
                    1
            );

            dbHelper.insertCart(cart);

            Toast.makeText(
                    context,
                    "Added to Cart",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public void filter(String query) {

        query = query.toLowerCase().trim();

        pizzaList.clear();

        if (query.isEmpty()) {

            pizzaList.addAll(fullList);

        } else {

            for (Pizza pizza : fullList) {

                if (pizza.getName().toLowerCase().contains(query)) {

                    pizzaList.add(pizza);

                }

            }
        }

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price;
        Button addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.pizzaImage);
            name = itemView.findViewById(R.id.pizzaName);
            price = itemView.findViewById(R.id.pizzaPrice);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}