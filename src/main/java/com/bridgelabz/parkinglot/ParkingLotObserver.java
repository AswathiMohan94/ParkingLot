package com.bridgelabz.parkinglot;

public interface ParkingLotObserver {

    public boolean capacityIsAvailable();
    public boolean capacityIsFull() throws ParkingLotException;
    default public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException{
        return true;
    }



}



