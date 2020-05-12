package com.bridgelabz.parkinglot;

public interface ParkingLotObserver {

    public void parkingLotIsFull();


    boolean AllotVacantSlot(Object vehicle) throws ParkingLotException;
}
