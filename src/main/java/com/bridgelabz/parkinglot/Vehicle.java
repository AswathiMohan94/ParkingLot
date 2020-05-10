package com.bridgelabz.parkinglot;

public class Vehicle {

    private String name;
    public String vehicleNumberPlate;
    public VehicleColor vehicleColor;
    public VehicleType vehicleType;


    public enum VehicleColor {WHITE,BLUE,RED}
    public enum VehicleType {TOYOTA}

    public Vehicle(String vehicleNumberPlate, VehicleColor colour, VehicleType vehicleType,String name) {
        this.vehicleNumberPlate=vehicleNumberPlate;
        this.vehicleColor = colour;
        this.vehicleType = vehicleType;
        this.name=name;
    }


}
