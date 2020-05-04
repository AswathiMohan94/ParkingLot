package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingAttendant implements ParkingLotObserver {
    private  int currentCapacity;
    private int occupiedSlots=0;
    private List slots;
    private boolean capacityIsAvailable;
    boolean capacityIsFull;

    public ParkingAttendant(int capacity) {
        this.slots = new ArrayList();
        this.currentCapacity=capacity;
    }
    @Override
    public boolean capacityIsFull() {
        return false;
    }

    @Override
    public boolean capacityIsAvailable() {
        return true;
    }
    @Override
    public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException {
        // if (a.values().stream().filter(value -> value.contains(1)).count() > 0) {
      //  if(slots.size() == (occupiedSlots-1) )
       //     throw new ParkingLotException("no slot available");
        if(vehicle!= null && occupiedSlots< currentCapacity) {
            this.slots.add(vehicle);
            occupiedSlots++;
            return this.capacityIsAvailable;
        }
        return  capacityIsFull;
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
