package com.example.pixxa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixxa.R;
import com.example.pixxa.database.DBHelper;
import com.example.pixxa.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    ArrayList<Cart> list;
    DBHelper dbHelper;

    public CartAdapter(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.cart_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Cart cart = list.get(position);

        holder.name.setText(cart.getPizzaName());
        holder.price.setText("₹" + cart.getPrice());
        holder.quantity.setText(String.valueOf(cart.getQuantity()));

        holder.btnPlus.setOnClickListener(v -> {

            dbHelper.increaseQuantity(cart.getId());

            cart.setQuantity(cart.getQuantity() + 1);

            holder.quantity.setText(String.valueOf(cart.getQuantity()));

        });

        holder.btnMinus.setOnClickListener(v -> {

            if (cart.getQuantity() > 1) {

                dbHelper.decreaseQuantity(cart.getId());

                cart.setQuantity(cart.getQuantity() - 1);

                holder.quantity.setText(String.valueOf(cart.getQuantity()));

            } else {

                dbHelper.decreaseQuantity(cart.getId());

                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION) {

                    list.remove(pos);

                    notifyItemRemoved(pos);
                }
            }
        });

        holder.deleteIcon.setOnClickListener(v -> {

            dbHelper.removeCartItem(cart.getId());

            int pos = holder.getAdapterPosition();

            if (pos != RecyclerView.NO_POSITION) {
                list.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, list.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, quantity;
        ImageView deleteIcon;
        TextView btnPlus;
        TextView btnMinus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cartPizzaName);
            price = itemView.findViewById(R.id.cartPrice);
            quantity = itemView.findViewById(R.id.cartQuantity);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
}