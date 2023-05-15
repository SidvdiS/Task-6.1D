package com.example.task61d;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.task61d.data.OrderDatabaseHelper;
import com.example.task61d.model.Order;
import com.example.task61d.util.TruckUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class AddDeliveryOrderActivity extends AppCompatActivity {
    EditText receiverName, goodType, weight, width, length, height;
    DatePicker datePicker;
    TimePicker timePicker;
    Spinner vehicleType;

    Button createOrder;

    private final static ArrayList<String> arrVehicleType = new ArrayList<>(Arrays.asList(
                                            TruckUtil.TYPE_BOX,TruckUtil.TYPE_FLATBED,TruckUtil.TYPE_LOG,
                                            TruckUtil.TYPE_MINI,TruckUtil.TYPE_REFRIGERATED,TruckUtil.TYPE_TANKER,
                                            TruckUtil.TYPE_TOW,TruckUtil.TYPE_VAN));

    //Current Date and Time
    Calendar calendar = Calendar.getInstance();
    Date currentDate = calendar.getTime();

    // Format the date and time using SimpleDateFormat
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    String receiverNameText, goodTypeText, vehicleTypeText, weightText, widthText, lengthText, heightText;

    String datePickerText = dateFormat.format(currentDate);
    String timePickerText = timeFormat.format(currentDate);


    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery_order);

        receiverName = findViewById(R.id.receiver_name);
        goodType = findViewById(R.id.good_type);
        weight = findViewById(R.id.weight);
        width = findViewById(R.id.width);
        length = findViewById(R.id.length);
        height = findViewById(R.id.height);
        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        vehicleType = findViewById(R.id.vehicle_type);
        createOrder = findViewById(R.id.create_order);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrVehicleType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleType.setAdapter(adapter);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                datePickerText = dateFormat.format(calendar.getTime());
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                timePickerText = timeFormat.format(calendar.getTime());
            }
        });

        vehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vehicleTypeText = arrVehicleType.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiverNameText =  receiverName.getText().toString();
                goodTypeText =  goodType.getText().toString();
                weightText =  weight.getText().toString();
                lengthText =  length.getText().toString();
                widthText=  width.getText().toString();
                heightText =  height.getText().toString();
                if(TextUtils.isEmpty(receiverNameText)){
                    showToast("Enter receiver name");
                    receiverName.setError("Enter receiver name");
                    return;
                }
                if(TextUtils.isEmpty(goodTypeText)){
                    showToast("Enter good type");
                    goodType.setError("Enter good type");
                    return;
                }
                if(TextUtils.isEmpty(weightText)){
                    showToast("Enter weight");
                    weight.setError("Enter weight");
                    return;
                }
                if(TextUtils.isEmpty(lengthText)){
                    showToast("Enter length");
                    length.setError("Enter length");
                    return;
                }
                if(TextUtils.isEmpty(widthText)){
                    showToast("Enter width");
                    width.setError("Enter width");
                    return;
                }
                if(TextUtils.isEmpty(heightText)){
                    showToast("Enter height");
                    height.setError("Enter height");
                    return;
                }

                Order order = new Order(receiverNameText, datePickerText, timePickerText, goodTypeText,
                        vehicleTypeText, Float.parseFloat(weightText), Float.parseFloat(widthText),
                        Float.parseFloat(lengthText), Float.parseFloat(heightText));

                OrderDatabaseHelper orderDatabaseHelper = new OrderDatabaseHelper(AddDeliveryOrderActivity.this);
                long insert = orderDatabaseHelper.insertOrder(order);
                if(insert>0){
                    showToast("New order created successfully");
                    Intent i = new Intent(AddDeliveryOrderActivity.this, MyOrdersActivity.class);
                    startActivity(i);
                }else{
                    showToast("Sorry! Could not create order. Please try again");
                }
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(AddDeliveryOrderActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}