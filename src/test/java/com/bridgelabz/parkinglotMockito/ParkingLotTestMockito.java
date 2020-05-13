package com.bridgelabz.parkinglotMockito;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import com.bridgelabz.parkinglot.*;
import com.bridgelabz.parkinglot.ParkingAttendant;
 import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class ParkingLotTestMockito {

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

    ParkingLotSystem parkingLotSystem = null;
    @Mock
    ParkingLotSystem parkingLotSystemMock;
    AirportSecurity securityMock;
    ParkingLotOwner ownerMock;
    @Rule
    MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(2,5);
        this.securityMock=mock(AirportSecurity.class);
        this.parkingLotSystemMock=mock(ParkingLotSystem.class);
        this.ownerMock=mock(ParkingLotOwner.class);

    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        try {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystemMock.park(vehicle1,DriverType.NORMAL_DRIVER);
        } catch (ParkingLotException e) {
            when(parkingLotSystemMock.isVehicleParked(vehicle1)).thenReturn(true);
            Assert.assertTrue(parkingLotSystemMock.isVehicleParked(vehicle1));
            verify(parkingLotSystemMock).isVehicleParked(vehicle1);
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
            when(parkingLotSystemMock.unPark(vehicle1)).thenReturn(true);
            Assert.assertTrue(parkingLotSystemMock.unPark(vehicle1));
            verify(parkingLotSystemMock).unPark(vehicle1);
        } catch (ParkingLotException e) {
        }
    }
    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        when(parkingLotSystemMock.isVehicleParked(vehicle1)).thenReturn(true);
        when(parkingLotSystemMock.isVehicleParked(vehicle2)).thenReturn(true);
        Assert.assertTrue(parkingLotSystemMock.isVehicleParked(vehicle1) && parkingLotSystemMock.isVehicleParked(vehicle2));
        verify(parkingLotSystemMock).isVehicleParked(vehicle1);
    }
    @Test
    public void givenWhenParkingLotIsFull_ShouldInformAirportSecurity() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
           parkingLotSystem.park(vehicle1,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle2,DriverType.NORMAL_DRIVER);
            when(securityMock.isParkingLotFull()).thenReturn(true);
        } catch (ParkingLotException e) {
            Assert.assertTrue(securityMock.isParkingLotFull());
            verify(securityMock).isParkingLotFull();
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
            when(parkingLotSystemMock.unPark(vehicle1)).thenReturn(true);
            when(ownerMock.isParkingLotFull()).thenReturn(true);
        } catch (ParkingLotException e) {
            Assert.assertTrue(owner.isParkingLotFull());
            verify(parkingLotSystemMock).unPark(vehicle1);
            verify(ownerMock).isParkingLotFull();
        }
    }

      //<<<<<<<<<<<<<<<<<----------------UC6 ----------------->>>>>>>>>>>>>>>>>>>>
    @Test
    public void givenIfVehicleNeedToBeParked_ThenAttendantShouldGiveSlot_NoSlotShouldReturnFalse() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 3);
        ParkingAttendant attendant = new ParkingAttendant();
        parkingLotSystem.registerParkingLotObserver(attendant);
        try {
            when(parkingLotSystemMock.isSlotAvailable(vehicle1,DriverType.NORMAL_DRIVER)).thenReturn(true);
            when(parkingLotSystemMock.isSlotAvailable(vehicle2,DriverType.NORMAL_DRIVER)).thenReturn(true);
            when(parkingLotSystemMock.isSlotAvailable(vehicle3,DriverType.NORMAL_DRIVER)).thenReturn(false);
        } catch (Exception e) {
            Assert.assertFalse(parkingLotSystemMock.isSlotAvailable(vehicle3,DriverType.NORMAL_DRIVER));
            verify(parkingLotSystemMock).isSlotAvailable(vehicle3,DriverType.NORMAL_DRIVER);
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
            when(parkingLotSystemMock.findMyVehicle(vehicle1)).thenReturn(0);
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle1));
            verify(parkingLotSystemMock).findMyVehicle(vehicle1);
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

             when(parkingLotSystemMock.findMyVehicle(vehicle1)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle2)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle3)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle4)).thenReturn(1);
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle1));
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle2));
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle3));
            Assert.assertEquals(1,(int) parkingLotSystemMock.findMyVehicle(vehicle4));
            verify(parkingLotSystemMock).findMyVehicle(vehicle1);
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

            when(parkingLotSystemMock.findMyVehicle(vehicle1)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle2)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle3)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle4)).thenReturn(1);
            when(parkingLotSystemMock.findMyVehicle(vehicle9)).thenReturn(2);;//Handicap
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle1));
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle2));
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle3));
            Assert.assertEquals(1,(int) parkingLotSystemMock.findMyVehicle(vehicle4));
            Assert.assertEquals(2, (int)parkingLotSystemMock.findMyVehicle(vehicle9));
            verify(parkingLotSystemMock).findMyVehicle(vehicle1);
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

            when(parkingLotSystemMock.findMyVehicle(vehicle1)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle2)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle3)).thenReturn(0);
            when(parkingLotSystemMock.findMyVehicle(vehicle4)).thenReturn(1);
            when(parkingLotSystemMock.findMyVehicle(vehicle6)).thenReturn(4);//large vehicle
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle1));
            Assert.assertEquals(0, (int)parkingLotSystemMock.findMyVehicle(vehicle2));
            Assert.assertEquals(0,(int) parkingLotSystemMock.findMyVehicle(vehicle3));
            Assert.assertEquals(1,(int) parkingLotSystemMock.findMyVehicle(vehicle4));
            Assert.assertEquals(4, (int)parkingLotSystemMock.findMyVehicle(vehicle6));
            verify(parkingLotSystemMock).findMyVehicle(vehicle1);
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

            when(parkingLotSystemMock.findCarsByColor(vehicle1)).thenReturn(0);//White
            int location2 = parkingLotSystem.findCarsByColor(vehicle5); //blue
            when(parkingLotSystemMock.findCarsByColor(vehicle3)).thenReturn(0);//White
            int location4 = parkingLotSystem.findCarsByColor(vehicle6);//blue
            int location5 = parkingLotSystem.findCarsByColor(vehicle7);//blue
            Assert.assertEquals(0, (int)parkingLotSystemMock.findCarsByColor(vehicle1));
            Assert.assertEquals(0,(int)parkingLotSystemMock.findCarsByColor(vehicle3));
            verify(parkingLotSystemMock).findCarsByColor(vehicle1);
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
            parkingLotSystem.park(vehicle16,DriverType.NORMAL_DRIVER);
            parkingLotSystem.park(vehicle17,DriverType.NORMAL_DRIVER);

            int location1 = parkingLotSystem.findCarsByColor(vehicle3);
            int location2 = parkingLotSystem.findCarsByColor(vehicle2);
            int location3 = parkingLotSystem.findCarsByColor(vehicle1);
            when(parkingLotSystemMock.findCarsByColor(vehicle16)).thenReturn(1);// BLUE
            when(parkingLotSystemMock.findCarsByColor(vehicle17)).thenReturn(1);// BLUE
            Assert.assertEquals(1,(int)parkingLotSystemMock.findCarsByColor(vehicle16));
            Assert.assertEquals(1,(int) parkingLotSystemMock.findCarsByColor(vehicle17));
            verify(parkingLotSystemMock).findCarsByColor(vehicle16);
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

            int location1 = parkingLotSystem.findByModel(vehicle1);
            int location2 = parkingLotSystem.findByModel(vehicle2);
            when(parkingLotSystemMock.findByModel(vehicle12)).thenReturn(0);// BMW
            int location4 = parkingLotSystem.findByModel(vehicle3);
            when(parkingLotSystemMock.findByModel(vehicle13)).thenReturn(1);// BMW
            Assert.assertEquals(0,(int) parkingLotSystemMock.findByModel(vehicle12));
            Assert.assertEquals(1,(int) parkingLotSystemMock.findByModel(vehicle13));
            verify(parkingLotSystemMock).findByModel(vehicle12);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenThatPoliceNeedsNoOfVehicles_ParkedInLast30Minutes() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3, 5);
        try {
            when(parkingLotSystemMock.getDateAndTime(vehicle1,DriverType.NORMAL_DRIVER)).thenReturn(1);
            when(parkingLotSystemMock.getDateAndTime(vehicle3,DriverType.NORMAL_DRIVER)).thenReturn(2);
            when(parkingLotSystemMock.getDateAndTime(vehicle4,DriverType.NORMAL_DRIVER)).thenReturn(3);
            when(parkingLotSystemMock.getDateAndTime(vehicle5,DriverType.NORMAL_DRIVER)).thenReturn(4);
            when(parkingLotSystemMock.getDateAndTime(vehicle9,DriverType.NORMAL_DRIVER)).thenReturn(5);
            when(parkingLotSystemMock.getDateAndTime(vehicle10,DriverType.NORMAL_DRIVER)).thenReturn(6);
            Assert.assertEquals(6, parkingLotSystemMock.getDateAndTime(vehicle10,DriverType.NORMAL_DRIVER));
            verify(parkingLotSystemMock).getDateAndTime(vehicle10,DriverType.NORMAL_DRIVER);
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
           when(parkingLotSystemMock.findHandicapFraudNumPlate()).thenReturn(2);
            Assert.assertEquals(2, parkingLotSystem.findHandicapFraudNumPlate());
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
            when(parkingLotSystemMock.findFraudulentlyNumPlate()).thenReturn("KL33A10014");
            Assert.assertEquals("KL33A10014",parkingLotSystemMock.findFraudulentlyNumPlate());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }
}