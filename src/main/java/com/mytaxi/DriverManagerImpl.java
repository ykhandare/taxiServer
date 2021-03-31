package com.mytaxi;

import com.mytaxi.mongoClasses.DataRepoManager;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yogesh on 31/3/21.
 */
@Repository
public class DriverManagerImpl implements  DriverManager{


    private Logger logger = LoggerFactory.getLogger(DriverManagerImpl.class);

    private final DataRepoManager dataRepoManager;

    @Autowired
    public DriverManagerImpl(DataRepoManager dataRepoManager)
    {
        this.dataRepoManager=dataRepoManager;
    }

    @Override
    public BaseResponse findDriverData(String id)throws Exception
    {
        BaseResponse baseResponse=new BaseResponse();
        try
        {

            Driver driver=dataRepoManager.findDriverData(id);

            if (driver!=null) {
                baseResponse.setDriver(driver);
                baseResponse.setSuccess("SUCCESS");
            }else {
                baseResponse.setSuccess("FAILED");
            }

        }catch (Exception e)
        {
            logger.error("exception occured while finding car details {}",e);
            baseResponse.setSuccess("FAILED");
        }
        return baseResponse;
    }


    @Override
    public boolean insertDriverData(Driver driver)throws Exception
    {
        boolean sucess=false;
        try
        {
            sucess=dataRepoManager.insertDriverData(driver);
        }catch (Exception e)
        {
            logger.error("exception occured while inserting car details {}",e);

            sucess=false;
        }
        return sucess;
    }

   public BaseResponse selectCar(CarSelectionRequest carSelectionRequest) throws CarAlreadyInUseException {
       BaseResponse baseResponse = new BaseResponse();

       Driver driver = dataRepoManager.findDriverData(carSelectionRequest.getDriverId());

       Car carData = dataRepoManager.findCarData(carSelectionRequest.getCarId());

       try {

              if (carData != null && driver != null && StringUtils.equalsIgnoreCase("online", driver.getStatus())) {

           if (carData.getCarOccupied() != null && carData.getCarOccupied().equalsIgnoreCase("N")) {
               carData.setCarOccupied("Y");
               CarHoldingData carHoldingData=new CarHoldingData();
               carHoldingData.setDriverId(driver.getId());
                carData.setCarHoldingData(carHoldingData);
               driver.setCardetails(carData);
               dataRepoManager.updateDriverData(driver);
               dataRepoManager.updateCarData(carData);
               baseResponse.setDriver(driver);
               baseResponse.setCardetails(carData);
           } else {
               throw new CarAlreadyInUseException("Car is already in use.");
           }
       } else if (carData != null && driver != null && StringUtils.equalsIgnoreCase("offline", driver.getStatus())) {
           throw new CarAlreadyInUseException("driver is offline");

       }
      } catch (CarAlreadyInUseException e)
       {
        baseResponse.setException(e.getMessage());
        baseResponse.setSuccess("FAILED");
       }



     return baseResponse;
    }

    public BaseResponse deSelectCar(CarSelectionRequest carSelectionRequest) throws CarAlreadyInUseException
    {
        BaseResponse baseResponse=new BaseResponse();

        Driver driver = dataRepoManager.findDriverData(carSelectionRequest.getDriverId());

        Car carData =dataRepoManager.findCarData(carSelectionRequest.getCarId());

        try {
            if (carData != null && driver != null && StringUtils.equalsIgnoreCase("online", driver.getStatus())) {
                if (carData.getCarOccupied() != null && carData.getCarOccupied().equalsIgnoreCase("N")) {
                    throw new CarAlreadyInUseException("car is not selected by driver.");

                } else if (carData.getCarOccupied() != null && carData.getCarOccupied().equalsIgnoreCase("Y")) {
                    if (carData.getCarHoldingData() != null && carData.getCarHoldingData().getDriverId().equalsIgnoreCase(carSelectionRequest.getDriverId())) {
                        carData.setCarOccupied("N");
                        driver.setCardetails(carData);
                        dataRepoManager.updateDriverData(driver);
                        dataRepoManager.updateCarData(carData);
                        baseResponse.setDriver(driver);
                        baseResponse.setCardetails(carData);
                    } else {
                        throw new CarAlreadyInUseException("Car is already in use.");
                    }
                }
            } else if (carData != null && driver != null && StringUtils.equalsIgnoreCase("offline", driver.getStatus())) {
                throw new CarAlreadyInUseException("driver is offline");

            }
        } catch (CarAlreadyInUseException e)
        {
            baseResponse.setException(e.getMessage());
            baseResponse.setSuccess("FAILED");
        }


        return baseResponse;
    }

    public boolean updateDriverStatus(Driver driver)
    {
        Driver driverStatus=new Driver();

        driverStatus.setStatus(driver.getStatus());
        return  dataRepoManager.updateDriverData(driver);
    }

   public BaseResponse searchCarByCharastics(BaseResponse baseResponse) throws Exception
    {

        BaseResponse result=new BaseResponse();
        if (baseResponse.getCardetails()!=null)
        {
            List<Car> carList=dataRepoManager.findCarDataByFilter(baseResponse.getCardetails());

            result.setCardetailsList(carList);
        }

        if (baseResponse.getDriver()!=null)
        {
            List<Driver> drivers=dataRepoManager.findDriverDataByFilter(baseResponse.getDriver());

            result.setDriverList(drivers);
        }

        if (result.getDriverList().isEmpty() && result.getCardetailsList().isEmpty())
        {
        throw new CarAlreadyInUseException("data not available for mentioned characteristics");
        }


        return result;

    }
}
