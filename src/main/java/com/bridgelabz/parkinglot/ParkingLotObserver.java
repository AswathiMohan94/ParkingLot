package com.bridgelabz.parkinglot;

public interface ParkingLotObserver {

    public void parkingLotIsFull();

    public void parkingLotIsEmpty();

    boolean AllotVacantSlot(Object vehicle) throws ParkingLotException;
}
