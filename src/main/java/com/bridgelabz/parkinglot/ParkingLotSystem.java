package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private int actualCapacity;
    private List vehicles;
    private List<ParkingLotObserver> observers;

    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /* public void registerSecurity(AirportSecurity airportSecurity) {
         this.security = airportSecurity;
     }*/
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    public void park(Object vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("vehicle already parked");
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("parking lot is full");
        }
        if(slotAvailable(vehicle))
            this.vehicles.add(vehicle);
    }

    public boolean slotAvailable(Object vehicle) {
        if (this.vehicles.size() <= this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.isSlotVacant();
                return true;
            }
        }
        return false;
    }

    public boolean isVehicleParked(Object vehicle) throws ParkingLotException {
        if(vehicle == null)
            throw new ParkingLotException("vehicle already parked");

        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle == null) return false;
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
                return true;
            }
        }
        return true;
    }

}