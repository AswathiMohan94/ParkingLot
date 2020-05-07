package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    public void park(Object vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("vehicle already parked");
        if (this.vehicles.size() == this.actualCapacity) {
            observers.forEach(observer -> observer.capacityIsFull());
            throw new ParkingLotException("parking lot is full");
        }
        this.vehicles.add(vehicle);
    }
      /*  if (isVehicleParked(vehicle))
            throw new ParkingLotException("vehicle already parked");
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("parking lot is full");
        }
               */


    public boolean isSlotAvailable(Object vehicle) throws ParkingLotException {
        try {
            observers.stream().forEach(observer -> {
                try {
                    observer.AllotVacantSlot(vehicle);
                } catch (ParkingLotException e) {
                    e.printStackTrace();
                }
            });
            if (vacancy == true) {
                park(vehicle);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

        //throw new ParkingLotException("Sorry no vacant slots");

            /*    for (ParkingLotObserver observer : observers) {
                vacancy=  observer.AllotVacantSlot(vehicle);
                if(vacancy == true){
                    park(vehicle);
                    return true;}

       /* observers.stream().forEach(observer -> {
            if (observer.AllotVacantSlot(vehicle) == true) {
                try {
                    park(vehicle);
                } catch (ParkingLotException e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
        throw new ParkingLotException("Sorry no vacant slots");*/


          /*    for (ParkingLotObserver observer : observers) {
                vacancy=  observer.AllotVacantSlot(vehicle);
                if(vacancy == true){
                    park(vehicle);
                    return true;
                }
            }
        throw new ParkingLotException("Sorry no vacant slots");

         /*
            throw new ParkingLotException("Sorry vacant slots");
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }*/


                public boolean isVehicleParked (Object vehicle) throws ParkingLotException {
                if (vehicle == null)
                    throw new ParkingLotException("vehicle already parked");

                if (this.vehicles.contains(vehicle))
                    return true;
                return false;
            }

        public boolean unPark (Object vehicle){
            if (vehicle == null) return false;
            vehicles.stream().filter(variable -> vehicles.contains(vehicle))
                    .filter(variable -> vehicles.remove(vehicle));
            observers.forEach(observer -> observer.capacityIsAvailable());
            return true;

        /*if (vehicle == null) return false;
         if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
                return true;
            }*/

        }
    }

