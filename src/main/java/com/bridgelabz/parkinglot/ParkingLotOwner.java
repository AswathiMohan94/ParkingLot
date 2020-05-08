package com.bridgelabz.parkinglot;

public class ParkingLotOwner implements ParkingLotObserver {
    private int capacity;
    private boolean isFullCapacity;
    public ParkingLotOwner(int capacity) {
        this.capacity=capacity;
    }

    public ParkingLotOwner() {

    }

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
        //return true;
        return isFullCapacity=true;
    }

}
