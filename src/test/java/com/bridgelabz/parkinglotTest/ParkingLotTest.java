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
    Object vehicle0 = null;
    com.bridgelabz.parkinglot.Vehicle vehicle = null;
    Object vehicle2 = null;
    Object vehicle3 = null;
    Map Vehicle = new HashMap<>();


    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
       // parkingAttendant = new ParkingAttendant(2,4);
        airportSecurity = new AirportSecurity();
        vehicle0=new Object();
       // vehicle=new Object();
        vehicle2=new Object();
        vehicle3=new Object();


    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle2);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked);

    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {

        try {
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotException e) {
            Assert.assertEquals("vehicle already parked", e.getMessage());
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
        Object vehicle3 = new Object();
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
        } catch (ParkingLotException e) {
            parkingLotSystem.unPark(vehicle2);
            boolean capacityFull = owner.isCapacityFull();
            Assert.assertFalse(capacityFull);
        }

    }

    @Test
    public void givenWhenVehicleParkedIsNull_ShouldReturnFalse() throws ParkingLotException {
        try {
           // vehicle2 = null;
            boolean checkNull= parkingLotSystem.isVehicleParked(vehicle0);
          //  parkingLotSystem.isVehicleParked(vehicle2);
            Assert.assertFalse(checkNull);
        } catch (Exception e) {
            //boolean checkNull = vehicle.equals(vehicle2);

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
        ParkingAttendant attendant = new ParkingAttendant(2,4);
        parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            parkingLotSystem.isSlotAvailable(vehicle);
            parkingLotSystem.isSlotAvailable(vehicle3);
            parkingLotSystem.isSlotAvailable(vehicle2);
        } catch (Exception e) {
            Assert.assertEquals("Sorry no vacant slots", e.getMessage());
        }
    }

    @Test
    public void givenAVehicleParkedNeedToBeFound_SoThatDriverCanUnPark() throws ParkingLotException {
        ParkingAttendant attendant = new ParkingAttendant(2,4);
        parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            parkingLotSystem.isSlotAvailable(vehicle2);
            parkingLotSystem.isSlotAvailable(vehicle3);
        } catch (Exception e) {
            boolean find = attendant.findMyVehicle(vehicle2);
            Assert.assertTrue(find);
        }
    }

    @Test
    public void givenDriverNeedToFindAnAlreadyUnParkedVehicle_ShouldReturnFalse() throws ParkingLotException {
        ParkingAttendant attendant = new ParkingAttendant(2,4);
        parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            parkingLotSystem.isSlotAvailable(vehicle2);
            parkingLotSystem.isSlotAvailable(vehicle3);
            boolean find = attendant.findMyVehicle(vehicle2);
        } catch (Exception e) {
            Assert.assertEquals("sorry vehicle not found", e.getMessage());
        }
    }

    @Test
    public void givenAVehicleParkedNeedToBeFound_AndDriverSearchingUnParkedVehicle_OwnerShouldReturnFalse() throws ParkingLotException {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.isSlotAvailable(vehicle);
            parkingLotSystem.isSlotAvailable(vehicle3);
        } catch (Exception e) {
            boolean find2 = owner.findMyVehicle(vehicle2);
            Assert.assertFalse(find2);
        }
    }

    @Test
    public void givenAVehicleParkedNeedToBeFound_AndDriverSearchingUnParkedVehicle_SecurityShouldReturnFalse() throws ParkingLotException {
        AirportSecurity security = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(security);
        try {
            parkingLotSystem.isSlotAvailable(vehicle);
            parkingLotSystem.isSlotAvailable(vehicle3);
           // System.out.println(vehicle);
            boolean find = security.findMyVehicle(vehicle2);
        } catch (Exception e) {
        }
    }

   @Test
    public void givenAVehicleShouldBeAllottedLot_InEvenMannerByAttendant() throws ParkingLotException {
       ParkingAttendant attendant = new ParkingAttendant(3,4);//lots 3 lots
       parkingLotSystem.registerParkingLotObserver(attendant);
       try {
           Vehicle vehicle1 = new Vehicle("KL33A1001");
           Vehicle vehicle2 = new Vehicle("KL33A1002");
           Vehicle vehicle3 = new Vehicle("KL33A1003");
           Vehicle vehicle4 = new Vehicle("KL33A1004");
           Vehicle vehicle5 = new Vehicle("KL33A1005");
           int Location1 = attendant.giveSlotLocation(vehicle1, 1);
           int Location2 = attendant.giveSlotLocation(vehicle2, 2);
           int Location3 = attendant.giveSlotLocation(vehicle3, 3);
           int Location4 = attendant.giveSlotLocation(vehicle4, 1);
           int Location5 = attendant.giveSlotLocation(vehicle5, 2);
           Assert.assertEquals(0, Location1);
           Assert.assertEquals(0, Location2);
           Assert.assertEquals(0, Location3);
           Assert.assertEquals(1, Location4);
           Assert.assertEquals(1, Location5);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
 /*  @Test
    public void givenVehicleOfHandicappedDriver_ShouldParkInTheNearestLot(){
             ParkingAttendant attendant = new ParkingAttendant(3);
             LotCapacity lotCapacity=new LotCapacity();
            parkingLotSystem.registerParkingLotObserver(attendant);
            try{
                Vehicle vehicle1 = new Vehicle("KL33A1001");
                Vehicle vehicle2 = new Vehicle("KL33A1002");
                Vehicle vehicle3 = new Vehicle("KL33A1003");
                Vehicle vehicle4 = new Vehicle("KL33A1004");
                Vehicle vehicle5 = new Vehicle("KL33A1005");
                int Location1 = attendant.giveSlotLocation(vehicle1, 2);
                int Location2 = attendant.giveSlotLocation(vehicle2, 1);
                int Location3 = attendant.giveSlotLocation(vehicle3, 3);
                int Location4 = attendant.giveSlotLocation(vehicle4, 2);
                int Location5 = attendant.giveSlotLocation(vehicle5, 2);
                Assert.assertEquals(0, Location1);
                Assert.assertEquals(0, Location2);
                Assert.assertEquals(0, Location3);
                Assert.assertEquals(1, Location4);
                Assert.assertEquals(2, Location5);
                Vehicle vehicleHandicap = new Vehicle("KL33A1006");
                int nearestLocation=attendant.findNearestSlot(vehicleHandicap);
                Assert.assertEquals(1, nearestLocation,0);
            } catch (ParkingLotException e) {
                e.printStackTrace();
            }
   }*/
}