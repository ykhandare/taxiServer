package com.mytaxi;

/**
 * Created by yogesh on 30/3/21.
 */
public interface CarManager {

    BaseResponse findCarData(long Id)throws Exception;

    boolean updateCarData(Car car)throws Exception;

    boolean deleteCarData(long Id)throws  Exception;

    boolean insertCarData(Car car)throws Exception;
}
