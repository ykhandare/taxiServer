package com.mytaxi;

/**
 * Created by yogesh on 31/3/21.
 */
public class CarSelectionRequest {

    private  String carId;

    private String driverId;



    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

}
