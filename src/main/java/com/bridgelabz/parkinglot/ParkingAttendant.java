package com.bridgelabz.parkinglot;

import java.util.ArrayList;

public class ParkingAttendant implements ParkingLotObserver {
    private int occupiedSlots = 0;
    private int currentCapacity = 0;
    ArrayList slots;

    @Override
    public void parkingLotIsFull() {

    }



    public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException {
       if (vehicle != null && occupiedSlots < currentCapacity) {
            occupiedSlots++;
            return true;
        }
        return false;
    }
}
