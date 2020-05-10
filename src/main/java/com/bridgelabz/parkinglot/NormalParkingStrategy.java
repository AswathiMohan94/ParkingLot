package com.bridgelabz.parkinglot;

import java.util.ArrayList;

public class NormalParkingStrategy implements ParkingStrategy {

    @Override
    public ArrayList<ParkingLot> parkVehicle(ArrayList<ParkingLot> parkingLots, Vehicle vehicle) throws ParkingLotException {
        Integer noOfSlots = parkingLots.stream().findFirst().get().listOfOccupiedSlots.size();
        for (Integer slotNumber = 0; slotNumber < noOfSlots; slotNumber++)
            for (ParkingLot lot : parkingLots)
                if (lot.listOfOccupiedSlots.get(slotNumber).vehicle == null) {
                    lot.listOfOccupiedSlots.get(slotNumber).vehicle = vehicle;
                    return parkingLots;
                }
        throw new ParkingLotException("parkingLotFull");
    }
}
