package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLot {
    public int thisParkingLotNumber;
    List<ParkingSlot> listOfOccupiedSlots;
    VehicleLocation location;
    Integer noOfVehicleParked = 0;

    ParkingLot(int thisParkingLotNumber, Integer parkingLotSize) {
        listOfOccupiedSlots = new ArrayList<>(parkingLotSize);
        location = new VehicleLocation();
        this.thisParkingLotNumber = thisParkingLotNumber;
        IntStream.range(0, parkingLotSize).forEach(slot -> this.listOfOccupiedSlots.add(new ParkingSlot(slot, null)));
    }
}