package com.bridgelabz.parkinglot;

public class ParkingSlot {
    public Integer slotPosition;
    public Vehicle vehicle;


    public ParkingSlot(Integer slotPosition, Vehicle vehicle) {
        this.vehicle = vehicle;
        this.slotPosition = slotPosition;
    }

}
