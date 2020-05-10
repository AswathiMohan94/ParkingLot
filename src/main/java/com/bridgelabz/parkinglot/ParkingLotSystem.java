package com.bridgelabz.parkinglot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLotSystem {
    private ArrayList<ParkingLot> parkingLots;
    private  ArrayList<String> time= new ArrayList<>();
    private int noOfLots;
    private int parkingLotCapacity;
    public ParkingLot parkingLot;
    List<ParkingLotObserver> observers;
    private int totalSlotOccupied;
    private Vehicle.VehicleColor colour;
    private Vehicle vehicle;
    private ParkingStrategy strategy;

    public ParkingLotSystem(int noOfLots, int parkingLotCapacity) {
        observers = new ArrayList();
        parkingLot = new ParkingLot(0, parkingLotCapacity);
        parkingLots = new ArrayList<ParkingLot>();
        this.parkingLotCapacity = parkingLotCapacity * noOfLots;

        this.noOfLots = noOfLots;
        IntStream.range(0, noOfLots).forEach(slotNumber -> this.parkingLots.add(new ParkingLot(slotNumber, parkingLotCapacity)));
    }

    public void registerParkingLotObserver(ParkingLotObserver owner) {
        observers.add(owner);
    }

    public void park(Vehicle vehicle, ParkingStrategy strategy) throws ParkingLotException {

        if (totalSlotOccupied == parkingLotCapacity * noOfLots) {
            for (ParkingLotObserver observer : observers)
                observer.parkingLotIsFull();
            throw new ParkingLotException("Parking lot is full");
        }
        parkingLots = strategy.parkVehicle(parkingLots, vehicle);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.isVehiclePark(vehicle));

    }

    public boolean isSlotAvailable(Vehicle vehicle, ParkingStrategy parkingStrategy) throws ParkingLotException {
        for (ParkingLotObserver observer : observers) {
            boolean vacancy = observer.AllotVacantSlot(vehicle);
            if (vacancy == true) {
                park(vehicle, new NormalParkingStrategy());
                return true;
            }
        }
        throw new ParkingLotException("Sorry no vacant slots");

    }

    public boolean unPark(Object vehicle) {
        if (vehicle == null) return false;
        parkingLots.stream().filter(variable -> parkingLots.contains(vehicle))
                .filter(variable -> parkingLots.remove(vehicle));
        // observers.forEach(observer -> observer.capacityIsAvailable());
        return true;
    }

    public Integer findMyVehicle(Vehicle vehicle, Vehicle.VehicleType vehicleType, Vehicle.VehicleColor colour) throws ParkingLotException {
        VehicleLocation location = new VehicleLocation();
        Integer noOfSlots = parkingLots.stream().findFirst().get().listOfOccupiedSlots.size();
        for (Integer slotNumber = 0; slotNumber < noOfSlots; slotNumber++)
            for (ParkingLot lot : parkingLots)
                if (lot.listOfOccupiedSlots.get(slotNumber).vehicle == vehicle) {
                    location.parkingSlot = slotNumber;
                    location.parkinglot = lot.thisParkingLotNumber;
                    return location.parkingSlot;

                }
        throw new ParkingLotException("No Such Vehicle Available");
    }
    public Integer findCarsByColor(Vehicle vehicle,Vehicle.VehicleType vehicleType,Vehicle.VehicleColor colour) throws ParkingLotException {
        if(colour==Vehicle.VehicleColor.WHITE)
            return findMyVehicle(vehicle, Vehicle.VehicleType.TOYOTA,Vehicle.VehicleColor.WHITE);
        if(colour==Vehicle.VehicleColor.BLUE)
            return findMyVehicle(vehicle, Vehicle.VehicleType.TOYOTA,Vehicle.VehicleColor.WHITE);
        return null;
    }
    // public int findCarsWithBlueColor(Vehicle vehicle, Vehicle.VehicleType vehicleType,Vehicle.VehicleColor colour) throws ParkingLotException {
      //  return findMyVehicle(vehicle,Vehicle.VehicleType.TOYOTA, Vehicle.VehicleColor.BLUE);

    public int findBMW(Vehicle vehicle, Vehicle.VehicleType vehicleType,Vehicle.VehicleColor colour) throws ParkingLotException {
        return findMyVehicle(vehicle,Vehicle.VehicleType.BMW,Vehicle.VehicleColor.BLUE);
    }

    public List findEmptySlots() {
        return parkingLot.listOfOccupiedSlots;
    }

    public int getDateAndTime(Vehicle vehicle, ParkingStrategy strategy) throws ParkingLotException {
        this.vehicle=vehicle;
        this.strategy=strategy;
        park(vehicle,strategy);
        LocalDateTime current = LocalDateTime.now().minusMinutes(30);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = current.format(format);
        time.add(formattedDateTime);
        int sizeOfTimeArray=time.size();
        return(sizeOfTimeArray);

    }
}

