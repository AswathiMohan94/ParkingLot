package com.bridgelabz.parkinglot;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isFullCapacity;
    @Override
    public boolean capacityIsFull() {
        isFullCapacity = true;
        return false;
    }

    @Override
    public boolean capacityIsAvailable() {

        return false;
    }

    @Override
    public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException {
        return false;
    }

    @Override
    public boolean findMyVehicle(Object vehicle) throws ParkingLotException {
        return false;
    }


    public boolean isCapacityFull() {
        //return true;
        return isFullCapacity=true;
    }

}
