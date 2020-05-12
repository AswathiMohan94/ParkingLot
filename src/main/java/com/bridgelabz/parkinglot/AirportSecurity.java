package com.bridgelabz.parkinglot;

public class AirportSecurity implements ParkingLotObserver {

    private Boolean parkingLotIsFull;


    public Boolean isParkingLotFull() {
        return parkingLotIsFull;
    }

    @Override
    public void parkingLotIsFull() {
        parkingLotIsFull = true;
    }

    @Override
    public boolean AllotVacantSlot(Object vehicle) {
        return false;
    }

}
