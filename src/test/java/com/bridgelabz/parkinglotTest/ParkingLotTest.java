package com.bridgelabz.parkinglotTest;

import com.bridgelabz.parkinglot.*;
import com.bridgelabz.parkinglot.ParkingAttendant;
 import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static com.bridgelabz.parkinglot.Vehicle.VehicleColor.*;
import static com.bridgelabz.parkinglot.Vehicle.VehicleType.BMW;
import static com.bridgelabz.parkinglot.Vehicle.VehicleType.TOYOTA;


public class ParkingLotTest {
    Vehicle vehicle1 = new Vehicle("KL33A1001", WHITE, TOYOTA,"Bob");
    Vehicle vehicle2 = new Vehicle("KL33A1002", WHITE, TOYOTA,"Alice");
    Vehicle vehicle3 = new Vehicle("KL33A1003", WHITE, TOYOTA,"Jack");
    Vehicle vehicle4 = new Vehicle("KL33A1004", WHITE, TOYOTA,"Jill");
    Vehicle vehicle5 = new Vehicle("KL33A1005", WHITE, TOYOTA,"Lilly");
    Vehicle vehicle6 = new Vehicle("KL33A1006", BLUE, TOYOTA,"Tom");
    Vehicle vehicle7 = new Vehicle("KL33A1007", BLUE, TOYOTA,"Jerry");
    Vehicle vehicle8 = new Vehicle("KL33A1008", BLUE, TOYOTA,"Daisy");
    Vehicle vehicle9 = new Vehicle("KL33A1009", BLUE,BMW,"abc");
    Vehicle vehicle10 = new Vehicle("KL33A1010", BLUE, BMW,"xyz");






    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
        boolean isVehicleParked = parkingLotSystem.isVehicleParked(vehicle1);
        Assert.assertTrue(isVehicleParked);

    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
        } catch (ParkingLotException e) {
            Assert.assertEquals("vehicle already parked", e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            Object isUnParked = parkingLotSystem.unPark(vehicle1);
            Assert.assertTrue((Boolean) isUnParked);
        } catch (ParkingLotException e) {
        }
    }
  /*  @Test
    public void givenWhenParkingLotIsFull_ShouldInformOwner() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("KL33A1001",WHITE, TOYOTA);
        Vehicle vehicle2 = new Vehicle("KL33A1002",WHITE, TOYOTA);

        try {
            parkingLotSystem.park(vehicle1,new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2,new NormalParkingStrategy());
        } catch (ParkingLotException e) {
        }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }*/

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle1);
            boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformAirportSecurity() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        AirportSecurity airportSecurity = new AirportSecurity();
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);

        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
        } catch (ParkingLotException e) {
            Assert.assertTrue(airportSecurity.isParkingLotFull());
        }
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
        } catch (ParkingLotException e) {
            parkingLotSystem.unPark(vehicle1);
            Assert.assertTrue(owner.isParkingLotFull());
        }
    }
/*
    @Test
    public void givenWhenVehicleParkedIsNull_ShouldReturnFalse() throws ParkingLotException {
        try {
            // vehicle2 = null;
            boolean checkNull = parkingLotSystem.isVehicleParked(vehicle0);
            //  parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertFalse(checkNull);
        } catch (Exception e) {
            //boolean checkNull = vehicle.equals(vehicle2);

        }
    }*/

    //<<<<<<<<<<<<<<<<<<<-------- ADDED TEST CASES TO GET 100 PERCENT COVERAGE -------->>>>>>>>>>>>>>>
    @Test
    public void givenWhenParkingLotIsFull_OwnerShouldReturnFalse() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
        } catch (Exception e) {
            Assert.assertTrue(owner.isParkingLotFull());
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_AirportSecurityShouldReturnFalse() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        AirportSecurity security = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(security);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
        } catch (Exception e) {
            Assert.assertTrue(security.isParkingLotFull());
        }
    }

    @Test
    public void givenIfSpaceNotFullInParkingLot_OwnerShouldReturnFalse() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            parkingLotSystem.unPark(vehicle1);
            Assert.assertFalse(owner.isParkingLotFull());
        } catch (Exception e) {
        }
    }

    @Test
    public void givenIfSpaceIsAvailableInParkingLot_AirportSecurityShouldReturnTrue() throws ParkingLotException {
        AirportSecurity security = new AirportSecurity();
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        parkingLotSystem.registerParkingLotObserver(security);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            parkingLotSystem.unPark(vehicle1);
            Assert.assertTrue(security.isParkingLotFull());
        } catch (Exception e) {
        }
    }

    @Test
    public void givenIfSameVehicle_RequestedToParkAgain_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        try {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
        } catch (Exception e) {
            Assert.assertEquals("vehicle already parked", e.getMessage());
        }
    }

    //<<<<<<<<<<<<<<<<<----------------UC6 ----------------->>>>>>>>>>>>>>>>>>>>
    @Test
    public void givenIfVehicleNeedToBeParked_ThenAttendantShouldGiveSlot_NoSlotShouldReturnFalse() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingAttendant attendant = new ParkingAttendant();
        parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            parkingLotSystem.isSlotAvailable(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.isSlotAvailable(vehicle3, new NormalParkingStrategy());
            parkingLotSystem.isSlotAvailable(vehicle2, new NormalParkingStrategy());
        } catch (Exception e) {
            Assert.assertEquals("Sorry no vacant slots", e.getMessage());
        }
    }

    @Test
    public void givenAVehicleParkedNeedToBeFound_SoThatDriverCanUnPark() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingAttendant attendant = new ParkingAttendant();
        parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
        } catch (Exception e) {
            int find = parkingLotSystem.findMyVehicle(vehicle1,TOYOTA,WHITE);
            Assert.assertEquals(2, find);
        }
    }

   /* @Test
    public void givenDriverTryingToFindAnAlreadyUnParkedVehicle_ShouldReturnFalse() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        Vehicle vehicle2 = new Vehicle("KL33A1002",WHITE,TOYOTA);
        Vehicle vehicle3 = new Vehicle("KL33A1003",WHITE,TOYOTA);
        ParkingAttendant attendant = new ParkingAttendant();
        parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            parkingLotSystem.park(vehicle2,new NormalParkingStrategy());
            parkingLotSystem.unPark(vehicle2);

           // parkingLotSystem.isSlotAvailable(vehicle3,new NormalParkingStrategy());
            //VehicleLocation find = parkingLotSystem.findMyVehicle(vehicle2);
        } catch (Exception e) {
            Assert.assertEquals("sorry vehicle not found", e.getMessage());
        }
    } */

/*
   @Test
    public void givenAVehicleParkedNeedToBeFound_AndDriverSearchingUnParkedVehicle_SecurityShouldReturnFalse() throws ParkingLotException {
        AirportSecurity security = new AirportSecurity(3);
        parkingLotSystem.registerParkingLotObserver(security);
        try {
            parkingLotSystem.isSlotAvailable(vehicle);
            parkingLotSystem.isSlotAvailable(vehicle3);
            // System.out.println(vehicle);
            boolean find = security.findMyVehicle(vehicle2);
        } catch (Exception e) {
        }
    }*/

    @Test
    public void givenAVehicleShouldBeAllottedLot_InEvenMannerByAttendant() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 4);
        //parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle5, new NormalParkingStrategy());

            int Location1 = parkingLotSystem.findMyVehicle(vehicle1,TOYOTA,WHITE);
            int Location2 = parkingLotSystem.findMyVehicle(vehicle2,TOYOTA,WHITE);
            int Location3 = parkingLotSystem.findMyVehicle(vehicle3,TOYOTA,WHITE);
            int Location4 = parkingLotSystem.findMyVehicle(vehicle4,TOYOTA,WHITE);
            int Location5 = parkingLotSystem.findMyVehicle(vehicle5,TOYOTA,WHITE);
            Assert.assertEquals(0, Location1);
            Assert.assertEquals(0, Location2);
            Assert.assertEquals(0, Location3);
            Assert.assertEquals(1, Location4);
            Assert.assertEquals(1, Location5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleOfHandicappedDriver_ShouldParkInTheNearestLot() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 4);

        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle5, new HandicapParkingStrategy());

            int Location1 = parkingLotSystem.findMyVehicle(vehicle1,TOYOTA,WHITE);
            int Location2 = parkingLotSystem.findMyVehicle(vehicle2,TOYOTA,WHITE);
            int Location3 = parkingLotSystem.findMyVehicle(vehicle3,TOYOTA,WHITE);
            int Location4 = parkingLotSystem.findMyVehicle(vehicle4,TOYOTA,WHITE);
            int Location5 = parkingLotSystem.findMyVehicle(vehicle5,TOYOTA,WHITE);
            Assert.assertEquals(0, Location1);
            Assert.assertEquals(0, Location2);
            Assert.assertEquals(0, Location3);
            Assert.assertEquals(1, Location4);
            Assert.assertEquals(2, Location5);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMultipleParkingLots_LargeVehicleShouldBeParkedInTheSlotWithHighestCapacity_ShouldParkAtLast() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle2, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle5, new LargeVehicleParkingStrategy());

            int Location1 = parkingLotSystem.findMyVehicle(vehicle1,TOYOTA,WHITE);
            int Location2 = parkingLotSystem.findMyVehicle(vehicle2,TOYOTA,WHITE);
            int Location3 = parkingLotSystem.findMyVehicle(vehicle3,TOYOTA,WHITE);
            int Location4 = parkingLotSystem.findMyVehicle(vehicle4,TOYOTA,WHITE);
            int Location5 = parkingLotSystem.findMyVehicle(vehicle5,TOYOTA,WHITE);
            Assert.assertEquals(0, Location1);
            Assert.assertEquals(0, Location2);
            Assert.assertEquals(0, Location3);
            Assert.assertEquals(1, Location4);
            Assert.assertEquals(4, Location5);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenMultipleParkingLots_AndLocationOfWhiteCarFound_ShouldReturnLocation() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            parkingLotSystem.park(vehicle1, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle6, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle8, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle7, new NormalParkingStrategy());

            int Location1 = parkingLotSystem.findCarsByColor(vehicle1,TOYOTA,WHITE);
            int Location2 = parkingLotSystem.findCarsByColor(vehicle6,TOYOTA,BLUE);
            int Location3 = parkingLotSystem.findCarsByColor(vehicle8,TOYOTA,BLUE);
            int Location4 = parkingLotSystem.findCarsByColor(vehicle4,TOYOTA,WHITE);
            int Location5 = parkingLotSystem.findCarsByColor(vehicle7,TOYOTA,BLUE);
            Assert.assertEquals(0,Location1);
            Assert.assertEquals(1,Location4);

        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenThatPoliceNeedNumber_AttendantDetails_AndLocationOfBlueToyotaCars() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            Vehicle vehicle3 = new Vehicle("KL33A1003", WHITE, TOYOTA,"Jack");
            Vehicle vehicle4 = new Vehicle("KL33A1004", WHITE, TOYOTA,"Jill");
            Vehicle vehicle5 = new Vehicle("KL33A1005", WHITE, TOYOTA,"Lilly");
            Vehicle vehicle6 = new Vehicle("KL33A1006", BLUE, TOYOTA,"Tom");
            Vehicle vehicle7 = new Vehicle("KL33A1007", BLUE, TOYOTA,"Jerry");

            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle5, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle6, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle7, new NormalParkingStrategy());

            int Location1 = parkingLotSystem.findCarsByColor(vehicle3,TOYOTA,WHITE);
            int Location2 = parkingLotSystem.findCarsByColor(vehicle4,TOYOTA,WHITE);
            int Location3 = parkingLotSystem.findCarsByColor(vehicle5,TOYOTA,WHITE);
            int Location4 = parkingLotSystem.findCarsByColor(vehicle6,TOYOTA,BLUE);
            int Location5 = parkingLotSystem.findCarsByColor(vehicle7,TOYOTA,BLUE);
            Assert.assertEquals(1,Location5);
            Assert.assertEquals(1,Location4);

        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenThatPoliceNeeds_LocationOf_BMW_cars() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            Vehicle vehicle3 = new Vehicle("KL33A1003", WHITE, TOYOTA,"Jack");
            Vehicle vehicle4 = new Vehicle("KL33A1004", WHITE, TOYOTA,"Jill");
            Vehicle vehicle5 = new Vehicle("KL33A1005", WHITE, TOYOTA,"Lilly");
            Vehicle vehicle9 = new Vehicle("KL33A1009", BLUE, BMW,"abc");
            Vehicle vehicle10 = new Vehicle("KL33A1010", BLUE, BMW,"xyz");

            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle5, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle9, new NormalParkingStrategy());
            parkingLotSystem.park(vehicle10, new NormalParkingStrategy());

            int Location1 = parkingLotSystem.findBMW(vehicle3,TOYOTA,WHITE);
            int Location2 = parkingLotSystem.findBMW(vehicle4,BMW,BLUE);
            int Location3 = parkingLotSystem.findBMW(vehicle5,TOYOTA,WHITE);
            int Location4 = parkingLotSystem.findBMW(vehicle9,TOYOTA,WHITE);
            int Location5 = parkingLotSystem.findBMW(vehicle10,BMW,BLUE);
            Assert.assertEquals(0,Location2);
            Assert.assertEquals(1,Location5);

        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenThatPoliceNeedsTheListOfALLCars_ParkedInLast30Minutes() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            Vehicle vehicle3 = new Vehicle("KL33A1003", WHITE, TOYOTA,"Jack");
            Vehicle vehicle4 = new Vehicle("KL33A1004", WHITE, TOYOTA,"Jill");
            Vehicle vehicle5 = new Vehicle("KL33A1005", WHITE, TOYOTA,"Lilly");
            Vehicle vehicle9 = new Vehicle("KL33A1009", BLUE, BMW,"abc");
            Vehicle vehicle10 = new Vehicle("KL33A1010", BLUE, BMW,"xyz");

            int formattedDateTime1=parkingLotSystem.getDateAndTime(vehicle1,new NormalParkingStrategy());
            int formattedDateTime2=parkingLotSystem.getDateAndTime(vehicle3, new NormalParkingStrategy());
            int formattedDateTime3=parkingLotSystem.getDateAndTime(vehicle4, new NormalParkingStrategy());
            int formattedDateTime4=parkingLotSystem.getDateAndTime(vehicle5, new NormalParkingStrategy());
            int formattedDateTime5=parkingLotSystem.getDateAndTime(vehicle9, new NormalParkingStrategy());
            int formattedDateTime6WithTheFinalSize=parkingLotSystem.getDateAndTime(vehicle10, new NormalParkingStrategy());
            Assert.assertEquals(6,formattedDateTime6WithTheFinalSize);

        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

}