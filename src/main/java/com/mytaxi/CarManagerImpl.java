package com.mytaxi;

import com.mytaxi.mongodb.DataRepoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yogesh on 30/3/21.
 */
@Service
public class CarManagerImpl implements  CarManager{
    private Logger logger = LoggerFactory.getLogger(CarManagerImpl.class);

    @Autowired
    private DataRepoManager dataRepoManager;

    @Override
    public BaseResponse findCarData(long id)throws Exception
    {
        BaseResponse baseResponse=new BaseResponse();
        try
        {

            Car car=dataRepoManager.findCarData(id);

            if (car!=null) {
                baseResponse.setCardetails(car);
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
    public boolean updateCarData(Car car)throws Exception
    {
        boolean success = false;
        try
        {
        success=dataRepoManager.updateCarData(car);
        }catch (Exception e)
        {
            logger.error("exception occured while updating car details {}",e);

            success=false;
        }
        return success;
    }

    @Override
    public boolean deleteCarData(long id)throws  Exception
    {
        boolean success = false;
        try
        {
        success=dataRepoManager.deleteCarData(id);
        }catch (Exception e)
        {
            logger.error("exception occured while deleting car details {}",e);

            success=false;
        }
        return success;
    }


    @Override
    public boolean insertCarData(Car car)throws Exception
    {
        boolean sucess=false;
        try
        {
        sucess=dataRepoManager.insertCarData(car);
        }catch (Exception e)
        {
            logger.error("exception occured while inserting car details {}",e);

            sucess=false;
        }
        return sucess;
    }
}
