package com.bridgelabz.parkinglot;

public class AirportSecurity implements ParkingLotObserver{
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
        return true;
       // return this.isFullCapacity=true;
    }
}
