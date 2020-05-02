package com.bridgelabz.parkinglot;

public class ParkingLotSystem {
    private Object vehicle;

    public ParkingLotSystem() {

    }

    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
                throw new ParkingLotException("parking lot is full");
        this.vehicle = vehicle;

        }


        public boolean unPark (Object vehicle){
            if (vehicle == null) return false;
            if (this.vehicle.equals(vehicle)) {
                this.vehicle = null;
                return true;
            }//else if(this.vehicle != vehicle)
            // return false;
            return false;

        }


    public boolean isVehicleParked(Object vehicle) {
       if(this.vehicle.equals(vehicle))
             return true;
       return false;
    }
}
