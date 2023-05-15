package com.example.task61d.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.task61d.model.Order;
import com.example.task61d.util.OrderUtil;

import java.util.ArrayList;

public class OrderDatabaseHelper extends SQLiteOpenHelper {


    public OrderDatabaseHelper(Context context) {
        super(context, OrderUtil.DATABASE_NAME, null, OrderUtil.DATABASE_VERSION);
    }

    private static final String CREATE_DATABASE_TABLE = "CREATE TABLE " + OrderUtil.TABLE_NAME +"("
            + OrderUtil.ORDER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "
            + OrderUtil.RECEIVER_NAME + " TEXT , " + OrderUtil.PICKUP_DATE + " TEXT , "
            + OrderUtil.PICKUP_TIME + " TEXT ," + OrderUtil.GOOD_TYPE + " TEXT ,"
            + OrderUtil.ORDER_WEIGHT + " REAL , " + OrderUtil.ORDER_LENGTH + " REAL , "
            + OrderUtil.ORDER_WIDTH + " REAL , " + OrderUtil.ORDER_HEIGHT + " REAL , "
            + OrderUtil.VEHICLE_TYPE + " TEXT)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderUtil.DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OrderUtil.RECEIVER_NAME, order.getReceiverName());
        contentValues.put(OrderUtil.PICKUP_DATE, order.getPickupDate());
        contentValues.put(OrderUtil.PICKUP_TIME, order.getPickupTime());
        contentValues.put(OrderUtil.GOOD_TYPE, order.getGoodType());
        contentValues.put(OrderUtil.ORDER_WEIGHT, order.getOrderWeight());
        contentValues.put(OrderUtil.ORDER_LENGTH, order.getOrderLength());
        contentValues.put(OrderUtil.ORDER_WIDTH, order.getOrderWidth());
        contentValues.put(OrderUtil.ORDER_HEIGHT, order.getOrderHeight());
        contentValues.put(OrderUtil.VEHICLE_TYPE, order.getVehicleType());
        long insert = db.insert(OrderUtil.TABLE_NAME, null, contentValues);
        return insert;
    }

    public ArrayList<Order> getAllOrders(){
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<Order> orders = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+OrderUtil.TABLE_NAME,null);

        while(cursor.moveToNext()){
            Order order = new Order();
            order.setOrderId(cursor.getInt(0));
            order.setReceiverName(cursor.getString(1));
            order.setPickupDate(cursor.getString(2));
            order.setPickupTime(cursor.getString(3));
            order.setGoodType(cursor.getString(4));
            order.setOrderWeight(cursor.getFloat(5));
            order.setOrderLength(cursor.getFloat(6));
            order.setOrderWidth(cursor.getFloat(7));
            order.setOrderHeight(cursor.getFloat(8));
            order.setVehicleType(cursor.getString(9));
            orders.add(order);
        }

        cursor.close();
        return orders;
    }

    public Order getOrderDetail(int orderID){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+OrderUtil.TABLE_NAME+ " WHERE "+OrderUtil.ORDER_ID+"="+orderID,null);
        Order order = new Order();
        while(cursor.moveToNext()){
            order.setOrderId(cursor.getInt(0));
            order.setReceiverName(cursor.getString(1));
            order.setPickupDate(cursor.getString(2));
            order.setPickupTime(cursor.getString(3));
            order.setGoodType(cursor.getString(4));
            order.setOrderWeight(cursor.getFloat(5));
            order.setOrderLength(cursor.getFloat(6));
            order.setOrderWidth(cursor.getFloat(7));
            order.setOrderHeight(cursor.getFloat(8));
            order.setVehicleType(cursor.getString(9));
        }
        cursor.close();
        return order;
    }
}
