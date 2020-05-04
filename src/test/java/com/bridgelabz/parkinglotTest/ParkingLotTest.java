package com.bridgelabz.parkinglotTest;

import com.bridgelabz.parkinglot.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    AirportSecurity airportSecurity = null;
    ParkingAttendant parkingAttendant = null;
    Object vehicle = null;
    Object vehicle2 = null;
    Object vehicle3 = null;
    Map Vehicle = new HashMap<>();


    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
        parkingAttendant = new ParkingAttendant(2);
        airportSecurity = new AirportSecurity();
        vehicle = new Object();
        vehicle2 = new Object();
        vehicle3 = new Object();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {

        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("parking lot is full", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
            boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        Object vehicle2 = new Object();
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotException e) {
            parkingLotSystem.unPark(vehicle);
            boolean capacityFull = owner.isCapacityFull();
            Assert.assertFalse(capacityFull);
        }

    }

    @Test
    public void givenWhenVehicleParkedIsNull_ShouldReturnFalse() throws ParkingLotException {
        try {
            vehicle2 = null;
            parkingLotSystem.isVehicleParked(vehicle);
            parkingLotSystem.isVehicleParked(vehicle2);
        } catch (Exception e) {
            boolean checkNull = vehicle.equals(vehicle2);
            Assert.assertFalse(checkNull);
        }
    }

    //<<<<<<<<<<<<<<<<<<<-------- ADDED TEST CASES TO GET 100 PERCENT COVERAGE -------->>>>>>>>>>>>>>>
    @Test
    public void givenWhenParkingLotIsFull_OwnerShouldReturnFalse() throws ParkingLotException {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
        } catch (Exception e) {
            boolean isCapacityFull = owner.isCapacityFull();
            Assert.assertTrue(isCapacityFull);
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_AirportSecurityShouldReturnFalse() throws ParkingLotException {
        AirportSecurity security = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(security);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
        } catch (Exception e) {
            boolean isCapacityFull = security.isCapacityFull();
            Assert.assertTrue(isCapacityFull);
        }
    }

    @Test
    public void givenIfSpaceIsAvailableInParkingLot_OwnerShouldReturnTrue() throws ParkingLotException {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            boolean isCapacityAvailable = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isCapacityAvailable);
        } catch (Exception e) {
        }
    }

    @Test
    public void givenIfSpaceIsAvailableInParkingLot_AirportSecurityShouldReturnTrue() throws ParkingLotException {
        AirportSecurity security = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(security);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            boolean isCapacityAvailable = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isCapacityAvailable);
        } catch (Exception e) {
        }
    }

    @Test
    public void givenIfSameVehicle_RequestedToParkAgain_ShouldReturnTrue() throws ParkingLotException {
        try {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle);
        } catch (Exception e) {
            Assert.assertEquals("vehicle already parked", e.getMessage());
        }
    }

    //<<<<<<<<<<<<<<<<<----------------UC6 ----------------->>>>>>>>>>>>>>>>>>>>
    @Test
    public void givenIfVehicleNeedToBeParked_ThenAttendantShouldGiveSlot_NoSlotShouldReturnFalse() throws ParkingLotException {
        ParkingAttendant attendant = new ParkingAttendant(2);
        parkingLotSystem.registerParkingLotObserver(attendant);
        try{
        boolean slotReceived = parkingLotSystem.isSlotAvailable(vehicle);
        boolean slotReceived3 = parkingLotSystem.isSlotAvailable(vehicle3);
        boolean slotReceived2 = parkingLotSystem.isSlotAvailable(vehicle2);
        } catch (Exception e) {
            Assert.assertEquals("Sorry vacant slots", e.getMessage());
        }
    }
}