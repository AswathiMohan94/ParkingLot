package com.bridgelabz.parkinglot;

public class AirportSecurity implements ParkingLotObserver{
    private int capacity;
    private boolean isFullCapacity;

    public AirportSecurity(int capacity) {
        this.capacity=capacity;
    }


    /*public AirportSecurity(int capacity) {
        this.capacity=capacity;
    }*/


    @Override
    public boolean capacityIsFull() {
        isFullCapacity = true;
        return false;
    }

    @Override
    public boolean capacityIsAvailable() {
        return false;
    }



    public boolean isCapacityFull() {
        return true;
       // return this.isFullCapacity=true;
    }
}
