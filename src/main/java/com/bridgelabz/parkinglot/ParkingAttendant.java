package com.bridgelabz.parkinglot;

import java.util.ArrayList;

public class ParkingAttendant implements ParkingLotObserver{
    ArrayList slots = new ArrayList();
    int slotCapacity = 5;
    private boolean capacityFull;
    private boolean capacityIsAvailable;


    @Override
    public void capacityIsFull() {
        capacityFull = false;

    }

    @Override
    public void capacityIsAvailable() {
        capacityIsAvailable = true;

    }

    public boolean isSlotVacant() {
        if(slots.size() == slotCapacity)
            return capacityFull;
        return capacityIsAvailable;
    }

}
