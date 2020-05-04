package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private int actualCapacity;
    private List vehicles;
    private List<ParkingLotObserver> observers;
    private boolean vacancy;

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
                this.vehicles.add(vehicle);


    }



    public boolean isSlotAvailable(Object vehicle) throws ParkingLotException {
        //if (this.vehicles.size() <= this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                vacancy=  observer.AllotVacantSlot(vehicle);
                if(vacancy == true){
                    park(vehicle);
                    return true;
                }
            }
       // return false;
        throw new ParkingLotException("Sorry vacant slots");

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