package com.bridgelabz.parkinglotTest;

import com.bridgelabz.parkinglot.*;
import com.bridgelabz.parkinglot.ParkingAttendant;
 import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class ParkingLotTest {
    Vehicle vehicle1 =new Vehicle("KL33A1001",VehicleDetails.TOYOTA,VehicleDetails.WHITE, VehicleDetails.SMALL,"Tom");
    Vehicle vehicle2 =new Vehicle("KL33A1002",VehicleDetails.TOYOTA,VehicleDetails.WHITE, VehicleDetails.SMALL,"Jerry");
    Vehicle vehicle3 =new Vehicle("KL33A1003",VehicleDetails.TOYOTA,VehicleDetails.WHITE, VehicleDetails.SMALL,"Tweety");
    Vehicle vehicle4 =new Vehicle("KL33A1004",VehicleDetails.TOYOTA,VehicleDetails.WHITE, VehicleDetails.SMALL,"Max");
    Vehicle vehicle5 =new Vehicle("KL33A1005",VehicleDetails.TOYOTA,VehicleDetails.BLUE, VehicleDetails.LARGE,"Casey");
    Vehicle vehicle6 =new Vehicle("KL33A1006",VehicleDetails.TOYOTA,VehicleDetails.BLUE, VehicleDetails.LARGE,"Eliza");
    Vehicle vehicle7 =new Vehicle("KL33A1007",VehicleDetails.TOYOTA,VehicleDetails.BLUE, VehicleDetails.LARGE,"Anna");
    Vehicle vehicle9 =new Vehicle("KL33A1009",VehicleDetails.TOYOTA,VehicleDetails.WHITE, VehicleDetails.SMALL,"Dora");
    Vehicle vehicle10 =new Vehicle("KL33A10010",VehicleDetails.TOYOTA,VehicleDetails.WHITE, VehicleDetails.SMALL,"Jack");
    Vehicle vehicle11 =new Vehicle("KL33A10010",VehicleDetails.TOYOTA,VehicleDetails.WHITE, VehicleDetails.SMALL,"Jill");
    Vehicle vehicle12 =new Vehicle("KL33A10012",VehicleDetails.BMW,VehicleDetails.BLUE, VehicleDetails.SMALL,"abc");
    Vehicle vehicle13 =new Vehicle("KL33A10013",VehicleDetails.BMW,VehicleDetails.BLUE, VehicleDetails.SMALL,"lmnop");
    Vehicle vehicle14 =new Vehicle("KL33A10014",VehicleDetails.BMW,VehicleDetails.BLUE, VehicleDetails.SMALL,"rst");
    Vehicle vehicle15 =new Vehicle("KL33A10014",VehicleDetails.BMW,VehicleDetails.BLUE, VehicleDetails.SMALL,"xyz");
    Vehicle vehicle16 =new Vehicle("KL33A10016",VehicleDetails.TOYOTA,VehicleDetails.BLUE, VehicleDetails.SMALL,"pqr");
    Vehicle vehicle17 =new Vehicle("KL33A10017",VehicleDetails.TOYOTA,VehicleDetails.BLUE, VehicleDetails.SMALL,"uvw");



    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        try {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
        } catch (ParkingLotException e) {
            boolean isVehicleParked = parkingLotSystem.isVehicleParked(vehicle1);
            Assert.assertTrue(isVehicleParked);
        }
    }
    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        try {
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
        } catch (ParkingLotException e) {
            Assert.assertEquals("vehicle already parked", e.getMessage());
        }
    }
    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            Object isUnParked = parkingLotSystem.unPark(vehicle1);
            Assert.assertTrue((Boolean) isUnParked);
        } catch (ParkingLotException e) {
        }
    }
    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
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
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
        } catch (ParkingLotException e) {
            Assert.assertTrue(airportSecurity.isParkingLotFull());
        }
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 2);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.unPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertTrue(owner.isParkingLotFull());
        }
    }

      //<<<<<<<<<<<<<<<<<----------------UC6 ----------------->>>>>>>>>>>>>>>>>>>>
    @Test
    public void givenIfVehicleNeedToBeParked_ThenAttendantShouldGiveSlot_NoSlotShouldReturnFalse() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingAttendant attendant = new ParkingAttendant();
        parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            parkingLotSystem.isSlotAvailable(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.isSlotAvailable(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.isSlotAvailable(vehicle3,DriverType.NORMAL_DRIVER);
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
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
        } catch (Exception e) {
            int find = parkingLotSystem.findMyVehicle(vehicle1);
            Assert.assertEquals(0, find);
        }
    }
    @Test
    public void givenAVehicleShouldBeAllottedLot_InEvenMannerByAttendant() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 4);
        try {
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle3,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle4,DriverType.NORMAL_DRIVER);

            int Location1 = parkingLotSystem.findMyVehicle(vehicle1);
            int Location2 = parkingLotSystem.findMyVehicle(vehicle2);
            int Location3 = parkingLotSystem.findMyVehicle(vehicle3);
            int Location4 = parkingLotSystem.findMyVehicle(vehicle4);
            Assert.assertEquals(0, Location1);
            Assert.assertEquals(0, Location2);
            Assert.assertEquals(0, Location3);
            Assert.assertEquals(1, Location4);
        } catch (Exception e) {

        }
    }

    @Test
    public void givenVehicleOfHandicappedDriver_ShouldParkInTheNearestLot() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 4);

        try {
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle3,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle4,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle9,DriverType.HANDICAP_DRIVER);

            int Location1 = parkingLotSystem.findMyVehicle(vehicle1);
            int Location2 = parkingLotSystem.findMyVehicle(vehicle2);
            int Location3 = parkingLotSystem.findMyVehicle(vehicle3);
            int Location4 = parkingLotSystem.findMyVehicle(vehicle4);
            int Location5 = parkingLotSystem.findMyVehicle(vehicle9);//Handicap
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
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle3,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle4,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle6,DriverType.NORMAL_DRIVER);//large vehicle

            int Location1 = parkingLotSystem.findMyVehicle(vehicle1);
            int Location2 = parkingLotSystem.findMyVehicle(vehicle2);
            int Location3 = parkingLotSystem.findMyVehicle(vehicle3);
            int Location4 = parkingLotSystem.findMyVehicle(vehicle4);
            int Location5 = parkingLotSystem.findMyVehicle(vehicle6);//large vehicle
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
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle3,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle4,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle5,DriverType.NORMAL_DRIVER);

            int Location1 = parkingLotSystem.findCarsByColor(vehicle1);//White
            int Location2 = parkingLotSystem.findCarsByColor(vehicle5); //blue
            int Location3 = parkingLotSystem.findCarsByColor(vehicle3);//White
            int Location4 = parkingLotSystem.findCarsByColor(vehicle6);//blue
            int Location5 = parkingLotSystem.findCarsByColor(vehicle7);//blue
            Assert.assertEquals(0, Location1);
            Assert.assertEquals(0, Location3);

        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenThatPoliceNeedNumber_AttendantDetails_AndLocationOfBlueToyotaCars() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            parkingLotSystem.park(vehicle3,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle5,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle6,DriverType.NORMAL_DRIVER);

            int Location1 = parkingLotSystem.findCarsByColor(vehicle3);
            int Location2 = parkingLotSystem.findCarsByColor(vehicle2);
            int Location3 = parkingLotSystem.findCarsByColor(vehicle1);
            int Location4 = parkingLotSystem.findCarsByColor(vehicle16);// BLUE
            int Location5 = parkingLotSystem.findCarsByColor(vehicle17);// BLUE
            Assert.assertEquals(1, Location5);
            Assert.assertEquals(1, Location4);

        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenThatPoliceNeeds_LocationOf_BMW_cars() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {

            parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle12,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle3,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle13,DriverType.NORMAL_DRIVER);

            int Location1 = parkingLotSystem.findByModel(vehicle1);
            int Location2 = parkingLotSystem.findByModel(vehicle2);
            int Location3 = parkingLotSystem.findByModel(vehicle12);//BMW
            int Location4 = parkingLotSystem.findByModel(vehicle3);
            int Location5 = parkingLotSystem.findByModel(vehicle13);//BMW
            Assert.assertEquals(0, Location3);
            Assert.assertEquals(1, Location5);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenThatPoliceNeedsNoOfVehicles_ParkedInLast30Minutes() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            int formattedDateTime1 = parkingLotSystem.getDateAndTime(vehicle1,DriverType.NORMAL_DRIVER);
            int formattedDateTime2 = parkingLotSystem.getDateAndTime(vehicle3,DriverType.NORMAL_DRIVER);
            int formattedDateTime3 = parkingLotSystem.getDateAndTime(vehicle4,DriverType.NORMAL_DRIVER);
            int formattedDateTime4 = parkingLotSystem.getDateAndTime(vehicle5,DriverType.NORMAL_DRIVER);
            int formattedDateTime5 = parkingLotSystem.getDateAndTime(vehicle9,DriverType.NORMAL_DRIVER);
            int formattedDateTime6WithTheFinalSize = parkingLotSystem.getDateAndTime(vehicle10,DriverType.NORMAL_DRIVER);
            Assert.assertEquals(6, formattedDateTime6WithTheFinalSize);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenThatPoliceNeedsInformationAndListOfCarsOfHandicappedDriver_ShouldReturnTheList() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            parkingLotSystem.park(vehicle12,DriverType.HANDICAP_DRIVER);
            parkingLotSystem.park(vehicle11,DriverType.HANDICAP_DRIVER);
            ArrayList lotData = parkingLotSystem.findHandicapFraudNumPlate();
            Assert.assertEquals(2, lotData.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenThatPoliceNeedsInformationAndListOfAllCarsParkedInALot_ShouldReturnTheList() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            parkingLotSystem.park(vehicle3,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle14,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle15,DriverType.NORMAL_DRIVER);
            Vehicle fraud = parkingLotSystem.findFraudulentlyNumPlate();
            System.out.println(fraud);
            Assert.assertEquals("KL33A10014",fraud.vehicleNumPlate);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }
}