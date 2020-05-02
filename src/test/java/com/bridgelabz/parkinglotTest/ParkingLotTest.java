package com.bridgelabz.parkinglotTest;

import com.bridgelabz.parkinglot.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue(){
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isParked = parkingLotSystem.park(new Object());
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        Object vehicle = new Object();
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }
}
