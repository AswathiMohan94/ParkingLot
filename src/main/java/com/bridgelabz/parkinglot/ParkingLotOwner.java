package com.bridgelabz.parkinglot;

public class ParkingLotOwner implements ParkingLotObserver {

    private Boolean parkingLotIsFull =false;

    @Override
    public void parkingLotIsFull() {
        parkingLotIsFull = true;
    }

    @Override
    public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException {
        return false;
    }

    public Boolean isParkingLotFull() {
        return true;
    }
}
