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
    List<String> froudPlate = new ArrayList<>();

 /*   public Vehicle(DriverType type, VehicleDetails color, VehicleDetails size) {
        this.type = type;
        this.color = color;
        this.size = size;
    }

    public Vehicle(DriverType type, VehicleDetails size, String name, String carNumber, VehicleDetails color, VehicleDetails model) {
        this.type = type;
        this.size = size;
        this.name = name;
        this.carNumber = carNumber;
        this.color = color;
        this.model = model;
        fraudulentPlateNumber();*/



    public Vehicle(String vehicleNumPlate, VehicleDetails model, VehicleDetails colour, VehicleDetails size,String name) {
       this.vehicleNumPlate=vehicleNumPlate;
        this.color=colour;
        this.size=size;
        this.name=name;
        this.model=model;

    }

    public LocalDateTime getTimeAndDate() {
        return time;
    }

    public String fraudulentPlateNumber() {
        if (!froudPlate.contains(this.carNumber))
            froudPlate.add(this.carNumber);
        return this.carNumber;
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
