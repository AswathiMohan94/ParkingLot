package com.bridgelabz.parkinglot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    public String vehicleNumPlate;
    private Vehicle vehicle;
    private VehicleDetails colour;
    public Integer lot;
    public VehicleDetails size;
    public VehicleDetails color;
    public VehicleDetails model;
    public String carNumber;
    public String name;
    public DriverType type;
    LocalDateTime time = LocalDateTime.now();

    public Vehicle(String vehicleNumPlate, VehicleDetails model, VehicleDetails colour, VehicleDetails size,String name) {
       this.vehicleNumPlate=vehicleNumPlate;
        this.color=colour;
        this.size=size;
        this.name=name;
        this.model=model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                " size=" + size +
                ", color=" + color +
                ", model=" + model +
                ", vehicleNumPlate='" + vehicleNumPlate + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
