package com.bridgelabz.parkinglot;

public interface ParkingLotObserver {
    public void capacityIsFull();
    public void capacityIsAvailable();
    public boolean isSlotVacant();


}
