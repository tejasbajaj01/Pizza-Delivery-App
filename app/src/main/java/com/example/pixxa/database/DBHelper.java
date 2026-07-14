package com.example.pixxa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pixxa.model.Cart;
import com.example.pixxa.model.Order;
import com.example.pixxa.model.User;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {private static final String DB_NAME = "Userbase.db";
    private static final int DB_VERSION =11;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE userbase(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name TEXT, username TEXT, phone TEXT, email TEXT, password TEXT)";
        db.execSQL(query);
        String cartTable = "CREATE TABLE cart(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pizza_name TEXT," +
                "price INTEGER," +
                "quantity INTEGER)";


        db.execSQL(cartTable);
        String orderTable =
                "CREATE TABLE orders(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "pizza_name TEXT," +
                        "price INTEGER," +
                        "quantity INTEGER," +
                        "address TEXT," +
                        "status TEXT," +
                        "order_time TEXT)";
        db.execSQL(orderTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS userbase");
        db.execSQL("DROP TABLE IF EXISTS cart");
        db.execSQL("DROP TABLE IF EXISTS orders");

        onCreate(db);
    }

    public void removeCartItem(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                "cart",
                "id=?",
                new String[]{String.valueOf(id)}
        );

        db.close();
    }
    public void increaseQuantity(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(
                "UPDATE cart SET quantity = quantity + 1 WHERE id=?",
                new Object[]{id}
        );

        db.close();
    }

    public void decreaseQuantity(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT quantity FROM cart WHERE id=?",
                new String[]{String.valueOf(id)}
        );

        if (cursor.moveToFirst()) {

            int qty = cursor.getInt(0);

            if (qty > 1) {

                db.execSQL(
                        "UPDATE cart SET quantity = quantity - 1 WHERE id=?",
                        new Object[]{id}
                );

            } else {

                db.delete(
                        "cart",
                        "id=?",
                        new String[]{String.valueOf(id)}
                );
            }
        }

        cursor.close();
        db.close();
    }
    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("username", user.getUsername());
        values.put("phone", user.getPhone());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        long result = db.insert(
                "userbase",
                null,
                values
        );
        return result;
    }
    public long insertCart(Cart cart) {

        SQLiteDatabase db = this.getWritableDatabase();

        // Check if pizza already exists in cart
        Cursor cursor = db.rawQuery(
                "SELECT id, quantity FROM cart WHERE pizza_name=?",
                new String[]{cart.getPizzaName()}
        );

        if (cursor.moveToFirst()) {

            int id = cursor.getInt(0);
            int quantity = cursor.getInt(1);

            ContentValues values = new ContentValues();
            values.put("quantity", quantity + 1);

            db.update(
                    "cart",
                    values,
                    "id=?",
                    new String[]{String.valueOf(id)}
            );

            cursor.close();
            db.close();

            return id;

        } else {

            ContentValues values = new ContentValues();
            values.put("pizza_name", cart.getPizzaName());
            values.put("price", cart.getPrice());
            values.put("quantity", 1);

            long result = db.insert(
                    "cart",
                    null,
                    values
            );

            cursor.close();
            db.close();

            return result;
        }
    }

    public ArrayList<User>
    getAllUsers(){
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM userbase", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String username = cursor.getString(2);
            String phone = cursor.getString(3);
            String email = cursor.getString(4);
            String password = cursor.getString(5);
            User user = new User (id, name, username, phone, email, password);
            list.add(user);
        }
        cursor.close();
        return list;
    }
    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM userbase WHERE username=? AND password=?",
                new String[]{username, password});

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        return exists;
    }
    public ArrayList<Cart> getCartItems(){

        ArrayList<Cart> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM cart",
                null
        );

        while(cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int quantity = cursor.getInt(3);

            list.add(new Cart(
                    id,
                    name,
                    price,
                    quantity
            ));
        }


        cursor.close();

        return list;
    }
    public void placeOrder(String address, String status, String time) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(
                "INSERT INTO orders(pizza_name, price, quantity, address, status, order_time) " +
                        "SELECT pizza_name, price, quantity, ?, ?, ? FROM cart",
                new Object[]{address, status, time}
        );

        db.execSQL("DELETE FROM cart");
    }
    public ArrayList<Order> getOrders() {

        ArrayList<Order> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM orders", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String pizza = cursor.getString(1);
            int price = cursor.getInt(2);
            int qty = cursor.getInt(3);
            String address = cursor.getString(4);
            String status = cursor.getString(5);
            String time = cursor.getString(6);

            list.add(new Order(id, pizza, price, qty, address, status, time));
        }

        cursor.close();
        return list;
    }
    public User getUser(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM userbase WHERE username=?",
                new String[]{username}
        );

        User user = null;

        if (cursor.moveToFirst()) {

            user = new User(
                    cursor.getInt(0),      // id
                    cursor.getString(1),   // name
                    cursor.getString(2),   // username
                    cursor.getString(3),   // phone
                    cursor.getString(4),   // email
                    cursor.getString(5)    // password
            );
        }

        cursor.close();
        db.close();

        return user;
    }


}
