package com.bridgelabz.parkinglot;

import java.util.Objects;

public class
ParkingSlot {
    public Integer slotPosition;
    public Vehicle vehicle;


    public ParkingSlot(Integer slotPosition, Vehicle vehicle) {
        this.vehicle = vehicle;
        this.slotPosition = slotPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this.vehicle == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return Objects.equals(vehicle, that.vehicle);
    }

}
