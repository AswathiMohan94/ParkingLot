package com.bridgelabz.parkinglot;

import java.util.ArrayList;

public class ParkingAttendant {
    ArrayList slots = new ArrayList();
    int slotCapacity = 2;
    int currentCapacity = 0;

    public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException {
       // if (a.values().stream().filter(value -> value.contains(1)).count() > 0) {
        if(slotCapacity == currentCapacity )
            throw new ParkingLotException("no slot available");
        if(vehicle!= null && slotCapacity != currentCapacity) {
            this.slots.add(vehicle);
            currentCapacity++;
            return true;
        }return  false;
    }



   /* @Override
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
*/
}
