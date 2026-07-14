package com.example.pixxa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pixxa.OrderTrackingActivity;
import com.example.pixxa.R;
import com.example.pixxa.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    ArrayList<Order> list;

    public OrderAdapter(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView pizza, status, price;

        public ViewHolder(View itemView) {
            super(itemView);

            pizza = itemView.findViewById(R.id.orderPizza);
            status = itemView.findViewById(R.id.orderStatus);
            price = itemView.findViewById(R.id.orderPrice);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.order_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Order order = list.get(position);

        holder.pizza.setText(order.getPizzaName());
        holder.price.setText("₹" + order.getPrice());
        holder.status.setText(order.getStatus());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, OrderTrackingActivity.class);

            intent.putExtra("order_id", order.getId());
            intent.putExtra("pizza_name", order.getPizzaName());
            intent.putExtra("price", order.getPrice());
            intent.putExtra("quantity", order.getQuantity());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}