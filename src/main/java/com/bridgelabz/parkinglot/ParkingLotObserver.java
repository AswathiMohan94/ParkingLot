package com.bridgelabz.parkinglot;

public interface ParkingLotObserver {
    public boolean capacityIsFull();
    public boolean capacityIsAvailable();
    public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException;
    public boolean findMyVehicle(Object vehicle) throws ParkingLotException;


    }



