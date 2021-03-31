package com.mytaxi.mongodb;

import com.mytaxi.Car;
import com.mytaxi.Driver;

import java.util.List;

/**
 * Created by yogesh on 30/3/21.
 */
public interface DataRepoManager {

    Car findCarData(long id);

    boolean updateCarData(Car car);

    boolean deleteCarData(long Id)throws  Exception;

    boolean insertCarData(Car car)throws Exception;

    Driver findDriverData(long Id);

    boolean insertDriverData(Driver driver)throws Exception;

    boolean updateDriverData(Driver driver);

    List<Car> findCarDataByFilter(Car car);

    List<Driver> findDriverDataByFilter(Driver driver);



}
