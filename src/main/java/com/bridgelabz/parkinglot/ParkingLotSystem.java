package com.bridgelabz.parkinglot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Set;
public class ParkingLotSystem {
    ArrayList<String> parkingData = new ArrayList<>();

    private ArrayList<ParkingLot> parkingLots;
    private ArrayList<String> time = new ArrayList<>();
    private int noOfLots;
    private int parkingLotCapacity;
    public ParkingLot parkingLot;
    List<ParkingLotObserver> observers;
    private int totalSlotOccupied;
    private Vehicle vehicle;
    private ParkingStrategy strategy;
    private Map<Integer, Vehicle> ParkingData;
    private String vehicleNumberPlate;
    private String name;
    ArrayList data = new ArrayList();
    ArrayList fraud = new ArrayList();
    private DriverType driverType;
    private String vehicleNumPlate;
    // Set<ParkingLot> fraud=new HashSet<ParkingLot>();

    public ParkingLotSystem(int noOfLots, int parkingLotCapacity) {
        observers = new ArrayList();
        parkingLot = new ParkingLot(0, parkingLotCapacity);
        parkingLots = new ArrayList<ParkingLot>();
        this.parkingLotCapacity = parkingLotCapacity;

        this.noOfLots = noOfLots;
        IntStream.range(0, noOfLots).forEach(slotNumber -> this.parkingLots.add(new ParkingLot(slotNumber, parkingLotCapacity)));
    }

    public void registerParkingLotObserver(ParkingLotObserver owner) {
        observers.add(owner);
    }

    public void park(Vehicle vehicle,DriverType driverType) throws ParkingLotException {
        for (ParkingLotObserver observer : observers)
            observer.parkingLotIsFull();
        parkingLots = driverType.carParking(parkingLots, vehicle);
        data.add(vehicle);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        parkingLots.stream().filter(variable -> parkingLots.contains(vehicle));
        return true;

    }

    public boolean isSlotAvailable(Vehicle vehicle,DriverType driverType) throws ParkingLotException {
        for (ParkingLotObserver observer : observers) {
            boolean vacancy = observer.AllotVacantSlot(vehicle);
            if (vacancy == true) {
                park(vehicle,driverType);
                return true;
            }
        }
        throw new ParkingLotException("Sorry no vacant slots");

    }

    public boolean unPark(Vehicle vehicle) {
        if (vehicle == null) return false;
        parkingLots.stream().filter(variable -> parkingLots.contains(vehicle))
                .filter(variable -> parkingLots.remove(vehicle));
        // observers.forEach(observer -> observer.capacityIsAvailable());
        return true;
    }

    public Integer findMyVehicle(Vehicle vehicle) throws ParkingLotException {
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


    public Integer findCarsByColor(Vehicle vehicle) throws ParkingLotException {
            return findMyVehicle(vehicle);
    }

    public int findByModel( Vehicle vehicle) throws ParkingLotException {
        return findMyVehicle(vehicle);
    }


    public int getDateAndTime(Vehicle vehicle,DriverType driverType) throws ParkingLotException {
        this.vehicle = vehicle;
        park(vehicle,driverType);
        LocalDateTime current = LocalDateTime.now().minusMinutes(30);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = current.format(format);
        time.add(formattedDateTime);
        int sizeOfTimeArray = time.size();
        return (sizeOfTimeArray);
    }

    public ArrayList findHandicapFraudNumPlate() throws ParkingLotException {
        data.stream().filter(variable -> fraud.add(vehicle)).collect(Collectors.toSet());
        return data;
    }


    public Vehicle findFraudulentlyNumPlate() throws ParkingLotException {

        Set<Vehicle> fraud = new HashSet<>(data);
        data.stream().filter(variable -> fraud.add(vehicle)).collect(Collectors.toSet());
        Vehicle fraudElement = fraud.stream().findFirst().get();
        return fraudElement;
    }


}
