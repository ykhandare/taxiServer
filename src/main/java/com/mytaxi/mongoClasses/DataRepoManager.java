package com.mytaxi.mongoClasses;

import com.mytaxi.Car;
import com.mytaxi.Driver;

import java.util.List;

/**
 * Created by yogesh on 30/3/21.
 */
public interface DataRepoManager {

    Car findCarData(String id);

    boolean updateCarData(Car car);

    boolean deleteCarData(String Id)throws  Exception;

    boolean insertCarData(Car car)throws Exception;

    Driver findDriverData(String Id);

    boolean insertDriverData(Driver driver)throws Exception;

    boolean updateDriverData(Driver driver);

    List<Car> findCarDataByFilter(Car car);

    List<Driver> findDriverDataByFilter(Driver driver);



}
