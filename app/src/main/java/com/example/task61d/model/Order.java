package com.example.task61d.model;

public class Order {
    private int orderID;
    private String receiverName, pickupDate, pickupTime, goodType, vehicleType;
    private float orderWeight, orderWidth, orderLength, orderHeight;

    public Order(int orderID, String receiverName, String pickupDate, String pickupTime, String goodType, String vehicleType, float orderWeight, float orderWidth, float orderLength, float orderHeight) {
        this.orderID = orderID;
        this.receiverName = receiverName;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.goodType = goodType;
        this.vehicleType = vehicleType;
        this.orderWeight = orderWeight;
        this.orderWidth = orderWidth;
        this.orderLength = orderLength;
        this.orderHeight = orderHeight;
    }

    public Order(String receiverName, String pickupDate, String pickupTime, String goodType, String vehicleType, float orderWeight, float orderWidth, float orderLength, float orderHeight) {
        this.receiverName = receiverName;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.goodType = goodType;
        this.vehicleType = vehicleType;
        this.orderWeight = orderWeight;
        this.orderWidth = orderWidth;
        this.orderLength = orderLength;
        this.orderHeight = orderHeight;
    }

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderId(int orderID) {
        this.orderID = orderID;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public float getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(float orderWeight) {
        this.orderWeight = orderWeight;
    }

    public float getOrderWidth() {
        return orderWidth;
    }

    public void setOrderWidth(float orderWidth) {
        this.orderWidth = orderWidth;
    }

    public float getOrderLength() {
        return orderLength;
    }

    public void setOrderLength(float orderLength) {
        this.orderLength = orderLength;
    }

    public float getOrderHeight() {
        return orderHeight;
    }

    public void setOrderHeight(float orderHeight) {
        this.orderHeight = orderHeight;
    }

    public void printOrder(){
        System.out.println(this.receiverName+", "+
        this.pickupDate+", "+
        this.pickupTime+", "+
        this.goodType+", "+
        this.vehicleType+", "+
        this.orderWeight+", "+
        this.orderWidth+", "+
        this.orderLength+", "+
        this.orderHeight);
    }
}
