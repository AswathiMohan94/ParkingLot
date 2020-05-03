package com.bridgelabz.parkinglot;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isFullCapacity;
    @Override
    public void capacityIsFull() {
        isFullCapacity = true;
    }

    @Override
    public void capacityIsAvailable() {

    }
    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }

}
