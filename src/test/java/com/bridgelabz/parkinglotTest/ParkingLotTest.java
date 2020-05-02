package com.bridgelabz.parkinglotTest;

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
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isParked = parkingLotSystem.park(new Object());
        Assert.assertTrue(isParked);
    }
    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {

        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.park(vehicle);
        Assert.assertFalse(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }
    //new


  /*  @Test
    public void givenVehicle_NotEqualTo () {
    }*/
}
