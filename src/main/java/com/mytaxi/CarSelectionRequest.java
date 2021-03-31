package com.mytaxi;

/**
 * Created by yogesh on 31/3/21.
 */
public class CarSelectionRequest {

    private  long carId;

    private long driverId;



    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

}
