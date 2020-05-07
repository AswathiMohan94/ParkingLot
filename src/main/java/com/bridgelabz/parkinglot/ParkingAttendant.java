package com.bridgelabz.parkinglot;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;
import static javax.swing.UIManager.get;

public class ParkingAttendant implements ParkingLotObserver {
    HashMap<String, Integer> parkingLotSet = new HashMap<>();
    private int numOfLots;
    private int currentCapacity;
    private int occupiedSlots = 0;
    ArrayList slots;
    private ArrayList lot1, lot2, lot3;

    private boolean capacityIsAvailable;
    boolean capacityIsFull;
    private Object lot;
    private Vehicle vehicle;
    int numOfVehiclesSlot1, numOfVehiclesSlot2, numOfVehiclesSlot3 = 0;

        public ParkingAttendant(int lot,int currentCapacity) {
            this.slots = new ArrayList();
            this.slots = new ArrayList();
            this.lot2 = new ArrayList();
            this.lot3 = new ArrayList();
            this.lot1 = new ArrayList();
            this.currentCapacity=currentCapacity;
    }
/*
    public ParkingAttendant(Vehicle vehicle) {
        this.vehicle = vehicle;
    }*/

    @Override
    public boolean capacityIsFull() {
        return capacityIsFull = false;
    }

    @Override
    public boolean capacityIsAvailable() {
        return capacityIsAvailable = true;
    }

    @Override
    public boolean AllotVacantSlot(Object vehicle)  {
        if (slots.size() == (occupiedSlots - 1))
            //throw new ParkingLotException("no slot available");
            return false;
        if (vehicle != null && occupiedSlots < currentCapacity) {
            this.slots.add(vehicle);
            occupiedSlots++;
            return true;
        }
        return false;
    }

    public boolean findMyVehicle(Object vehicle) throws ParkingLotException {
        if (slots.contains(vehicle))
            return true;
        throw new ParkingLotException("sorry vehicle not found");
    }

    public int giveSlotLocation(Vehicle vehicle, int lot) throws ParkingLotException {
        // ArrayList l = (ArrayList) Stream.iterate(0, i -> i + 1).limit(5).map(i -> Integer.toString(i)).collect(Collectors.toList());
        this.vehicle = vehicle;
        this.lot = lot;
        if (vehicle != null && lot == 1) {
            this.lot1.add(vehicle);
            int location = lot1.indexOf(vehicle);
            numOfVehiclesSlot1++;
            return location;
        } else if (vehicle != null && lot == 2) {
            this.lot = lot2.add(vehicle);
            int location = lot2.indexOf(vehicle);
            numOfVehiclesSlot2++;
            return location;
        } else if (vehicle != null && lot == 3) {
            this.lot = lot3.add(vehicle);
            int location = lot3.indexOf(vehicle);
            numOfVehiclesSlot3++;
            return location;
        }
        throw new ParkingLotException("vehicle no found");

    }

 /*   public int findNearestParkingLot() {
        int nearestParkingLot = IntStream.range(0, parkingLotSet.size()).findFirst().orElse(-1);
        return nearestParkingLot;
    }
*/
    public Integer findNearestSlot(Vehicle vehicle) {
        int one;
        int two;
        int three;
        parkingLotSet.put("ParkingLot1", IntStream.range(0, lot1.size()).filter(i -> lot1.get(i) == null).findFirst().orElse(-1));
        parkingLotSet.put("ParkingLot2", IntStream.range(0, lot2.size()).filter(i -> lot2.get(i) == null).findFirst().orElse(-1));
        parkingLotSet.put("ParkingLot3", IntStream.range(0, lot3.size()).filter(i -> lot3.get(i) == null).findFirst().orElse(-1));
        Map<String, Integer> nearestLot = parkingLotSet.entrySet().stream().sorted(comparingByValue()).
                collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        return nearestLot.get(0);

    }
}









   /* @Override
    public void capacityIsFull() {
        capacityFull = false;

    }

    @Override
    public void capacityIsAvailable() {
        capacityIsAvailable = true;

    }

    public boolean isSlotVacant() {
        if(slots.size() == slotCapacity)
            return capacityFull;
        return capacityIsAvailable;
    }
*/

