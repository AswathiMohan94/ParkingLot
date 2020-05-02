package com.bridgelabz.parkinglotTest;

import com.bridgelabz.parkinglot.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue(){
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(new Object());
        boolean isParked = parkingLotSystem.park(new Object());
        Assert.assertTrue(isParked);
    }

}
