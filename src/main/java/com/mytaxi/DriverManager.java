package com.mytaxi;

/**
 * Created by yogesh on 31/3/21.
 */
public interface DriverManager {
    BaseResponse findDriverData(String Id)throws Exception;

    boolean insertDriverData(Driver driver)throws Exception;

    BaseResponse selectCar(CarSelectionRequest carSelectionRequest) throws CarAlreadyInUseException;
    BaseResponse deSelectCar(CarSelectionRequest carSelectionRequest) throws Exception;
    boolean updateDriverStatus(Driver driver);

    BaseResponse searchCarByCharastics(BaseResponse baseResponse) throws Exception;


}
