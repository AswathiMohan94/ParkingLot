package com.bridgelabz.parkinglot;

public class Vehicle {

    public String vehicleNumberPlate;
    public VehicleColor vehicleColor;
    public VehicleType vehicleType;


    public enum VehicleColor {WHITE,VIOLET,RED}
    public enum VehicleType {TOYOTA}

    public Vehicle(String vehicleNumberPlate, VehicleColor colour, VehicleType vehicleType) {
        this.vehicleNumberPlate=vehicleNumberPlate;
        this.vehicleColor = colour;
        this.vehicleType = vehicleType;
    }


}
