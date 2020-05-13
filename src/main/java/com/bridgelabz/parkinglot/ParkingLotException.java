package com.bridgelabz.parkinglot;

public class ParkingLotException extends Exception{

    public   ExceptionType type;

    public ParkingLotException(String no_such_vehicle_available) {
    }

    public enum ExceptionType {
        SORRY_NO_VACANT_SLOTS,NO_SUCH_VEHICLE_AVAILABLE

    }

   public ParkingLotException(String message,ExceptionType type) {
        super(message);
        this.type=type;
    }
}
