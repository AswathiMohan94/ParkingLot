package com.bridgelabz.parkinglotTest;

import com.bridgelabz.parkinglot.AirportSecurity;
import com.bridgelabz.parkinglot.ParkingLotException;
import com.bridgelabz.parkinglot.ParkingLotOwner;
import com.bridgelabz.parkinglot.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    Object vehicle2 = null;
    Object vehicle3 = null;

    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
        vehicle = new Object();
        vehicle2 = new Object();
        vehicle3 = new Object();
    }
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue(){
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked =parkingLotSystem.isVehicleParked(vehicle);
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
            Assert.assertEquals("parking lot is full",e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) { }
    }
    @Test
    public void givenWhenParkingLotIsFull_ShouldInformOwner () {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) { }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles(){
        Object vehicle2= new Object();
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            boolean isParked1 =parkingLotSystem.isVehicleParked(vehicle);
            boolean isParked2 =parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) { }
    }
    @Test
    public void givenWhenParkingLotIsFull_ShouldInformAirportSecurity () {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) { }
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
    @Test
    public void givenWhenParkingLotIsFull_OwnerShouldReturnFalse() throws ParkingLotException {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
        } catch (Exception e) {
            boolean isCapacityFull = vehicle.equals(vehicle2);
            Assert.assertFalse(isCapacityFull);
        }
    }

}
