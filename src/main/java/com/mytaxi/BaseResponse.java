package com.mytaxi;

import java.util.List;

/**
 * Created by yogesh on 30/3/21.
 */
public class BaseResponse {

    private Car cardetails;

    private String success;

    private Driver driver;

    private List<Car> cardetailsList;

    private List<Driver> driverList;




    public List<Car> getCardetailsList() {
        return cardetailsList;
    }

    public void setCardetailsList(List<Car> cardetailsList) {
        this.cardetailsList = cardetailsList;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }


    public Car getCardetails() {
        return cardetails;
    }

    public void setCardetails(Car cardetails) {
        this.cardetails = cardetails;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


}
