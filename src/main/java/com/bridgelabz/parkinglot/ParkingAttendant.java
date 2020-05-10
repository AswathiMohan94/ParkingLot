package com.bridgelabz.parkinglot;

import java.util.ArrayList;

public class ParkingAttendant implements ParkingLotObserver {
    private int occupiedSlots = 0;
    private int currentCapacity = 0;
    ArrayList slots;

    @Override
    public void parkingLotIsFull() {

    }

    @Override
    public void parkingLotIsEmpty() {

    }

    public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException {
        // if (slots.size() == (occupiedSlots - 1))
        //   throw new ParkingLotException("no slot available");

        if (vehicle != null && occupiedSlots < currentCapacity) {
            occupiedSlots++;
            return true;
        }
        return false;
    }

    public boolean findMyVehicle(Object vehicle) throws ParkingLotException {
        slots.stream().filter(variable -> slots.contains(vehicle));
        return true;
    }
}
