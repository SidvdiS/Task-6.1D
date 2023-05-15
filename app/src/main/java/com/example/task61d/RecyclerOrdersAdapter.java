package com.example.task61d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task61d.model.Order;
import com.example.task61d.util.OrderUtil;

import java.util.ArrayList;

public class RecyclerOrdersAdapter extends RecyclerView.Adapter<RecyclerOrdersAdapter.OrdersViewHolder> {
    Context context;

    ArrayList<Order> orders;

    public RecyclerOrdersAdapter(Context context, ArrayList<Order> orders){
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_row,parent,false);
        OrdersViewHolder holder = new OrdersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.receiverName.setText(orders.get(position).getReceiverName());
        holder.dateAndTime.setText(orders.get(position).getPickupDate()+"\n"+orders.get(position).getPickupTime());
        holder.goodType.setText(orders.get(position).getGoodType());
        holder.vehicleType.setText(orders.get(position).getVehicleType());
        holder.orderID.setText("("+orders.get(position).getOrderID()+")");
        holder.cardRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, OrderDetailsActivity.class);
                i.putExtra(OrderUtil.ORDER_ID, orders.get(position).getOrderID());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {

        TextView receiverName, dateAndTime, goodType, vehicleType, orderID;
        CardView cardRoot;
        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverName = itemView.findViewById(R.id.receiver_name);
            dateAndTime = itemView.findViewById(R.id.date_and_time);
            goodType = itemView.findViewById(R.id.good_type);
            vehicleType = itemView.findViewById(R.id.vehicle_type);
            orderID = itemView.findViewById(R.id.order_ID);
            cardRoot = itemView.findViewById(R.id.order_card_root);
        }
    }
}
