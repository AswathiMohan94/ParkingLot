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

    @Override
    public boolean isSlotVacant() {

        return false;
    }

    public boolean isCapacityFull() {
        //return true;
        return isFullCapacity=true;
    }

}
