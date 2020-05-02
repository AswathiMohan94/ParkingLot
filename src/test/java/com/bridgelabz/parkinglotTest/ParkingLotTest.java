package com.bridgelabz.parkinglotTest;

import com.bridgelabz.parkinglot.ParkingLotException;
import com.bridgelabz.parkinglot.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
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
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }
    //new


  /*  @Test
    public void givenVehicle_NotEqualTo () {
    }*/
}
