package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.List;



public class ParkingAttendant implements ParkingLotObserver {
    private int currentCapacity;
    private int occupiedSlots = 0;
    private ArrayList slots;
    private ArrayList slot1 ;
    List slot2;
    List slot3;
    private boolean capacityIsAvailable;
    boolean capacityIsFull;
    private Object lot;
    private Vehicle vehicle;

    public ParkingAttendant(int capacity) {
        this.slots = new ArrayList();
        this.currentCapacity = capacity;
        this.slots = new ArrayList();
        this.slot2 = new ArrayList();
        this.slot3 = new ArrayList();
        this.slot1 = new ArrayList();


    }
     public ParkingAttendant(Vehicle vehicle, int lot) {
        this.vehicle = vehicle;
        this.lot = lot;
    }

    @Override
    public boolean capacityIsFull() {
        return capacityIsFull = false;
    }

    @Override
    public boolean capacityIsAvailable() {
        return capacityIsAvailable = true;
    }

    @Override
    public boolean AllotVacantSlot(Object vehicle) throws ParkingLotException {
        if (slot1.size() == (occupiedSlots - 1))
            throw new ParkingLotException("no slot available");
        if (vehicle != null && occupiedSlots < currentCapacity) {
            this.slot1.add(vehicle);
            occupiedSlots++;
            return true;
        }
        return false;
    }

    public boolean findMyVehicle(Object vehicle) throws ParkingLotException {
        if (slots.contains(vehicle))
            return true;
        throw new ParkingLotException("sorry vehicle not found");
    }

    public int giveSlotLocation(Vehicle vehicle, int lot) throws ParkingLotException {
        this.vehicle = vehicle;
        this.lot = lot;
        if (vehicle != null && lot == 1) {
                this.slot1.add(vehicle);
                int location = slot1.indexOf(vehicle);
                return location;
            }
        else if(vehicle != null && lot == 2){
            this.lot = slot2.add(vehicle);
            int location = slot2.indexOf(vehicle);
            return location;
        }
        else if(vehicle != null && lot == 3) {
            this.lot = slot3.add(vehicle);
            int location = slot3.indexOf(vehicle);
            return location;
        }
        throw new ParkingLotException("vehicle no found");
    }

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

