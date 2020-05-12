package com.bridgelabz.parkinglot;

import java.util.ArrayList;

public enum DriverType {
    NORMAL_DRIVER {
        @Override
        public ArrayList carParking(ArrayList parkingLots, Vehicle vehicle) throws ParkingLotException {
            if (vehicle.size.toString().contains("SMALL")) {
                parkingLots=new NormalParkingStrategy().parkVehicle(parkingLots, vehicle);
                ;
            } else {
                parkingLots=new LargeVehicleParkingStrategy().parkVehicle(parkingLots, vehicle);
            }
            return parkingLots;
        }
    }, HANDICAP_DRIVER {
        @Override
        public ArrayList carParking(ArrayList parkingLots, Vehicle vehicle) throws ParkingLotException {
            new HandicapParkingStrategy().parkVehicle(parkingLots, vehicle);
            return parkingLots;
        }
    };

    public abstract ArrayList carParking(ArrayList parkingLots, Vehicle vehicle) throws ParkingLotException;
}
