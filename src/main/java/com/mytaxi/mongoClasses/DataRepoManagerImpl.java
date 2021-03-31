package com.mytaxi.mongoClasses;

import com.mytaxi.Car;
import com.mytaxi.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
*
 * Created by yogesh on 30/3/21.
*/




@Repository
public class DataRepoManagerImpl implements DataRepoManager {


    private final MongoTemplate mongoTemplate;

    @Autowired
    public DataRepoManagerImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Car findCarData(String id)
    {
       Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));


        return mongoTemplate.findOne(query, Car.class);

    }

    @Override
    public boolean updateCarData(Car car)
    {
        boolean response=false;

           Query query = new Query();

            query.addCriteria(Criteria.where("id").is(car.getId()));

            Update update = new Update();

            if (car.getLicense_plate() != null) {
                update.set("license_plate", car.getLicense_plate());
            }

            if (car.getEngine_type() != null) {
                update.set("engine_type", car.getEngine_type());
            }

            if (car.getManufacturer() != null) {
                update.set("manufacturer", car.getManufacturer());
            }
            if (car.getModel() != null) {
                update.set("model", car.getModel());
            }

            if (car.getRating() != 0) {
                update.set("rating", car.getRating());
            }

            if (car.getSeat_cnt() != 0) {
                update.set("seat_cnt", car.getSeat_cnt());
            }

            if (car.getCarOccupied()!=null)
            {
                update.set("carOccupied", car.getCarOccupied());

            }

            if (car.getCarHoldingData()!=null)
            {
                update.set("carHoldingData", car.getCarHoldingData());

            }

            Car writeResult = mongoTemplate.findAndModify(query, update, Car.class);
            if (writeResult != null) {
                response = true;
            }

        return response;
    }

    @Override
    public boolean deleteCarData(String id)throws Exception
    {
        boolean response =false;

        try {


            Query query = new Query();

            query.addCriteria(Criteria.where("id").is(id));

            mongoTemplate.remove(query, Car.class);


                response=true;

        }catch (Exception e)
        {
            response=false;
        }
        return response;

    }

    @Override
    public boolean insertCarData(Car car)throws Exception
    {
        boolean response =false;

        try {
            mongoTemplate.insert(car);
            response=true;
        }catch (Exception e)
        {
            response =false;
        }
       return response;

    }

    @Override
    public Driver findDriverData(String id)
    {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));


        return mongoTemplate.findOne(query, Driver.class);

    }

    @Override
    public boolean insertDriverData(Driver driver)throws Exception
    {
        boolean response =false;

        try {
            mongoTemplate.insert(driver);
            response=true;
        }catch (Exception e)
        {
            response =false;
        }
        return response;

    }

    @Override
    public boolean updateDriverData(Driver driver)
    {
        boolean response=false;

           Query query = new Query();

            query.addCriteria(Criteria.where("id").is(driver.getId()));

            Update update = new Update();

            if (driver.getCardetails() != null) {
                update.set("cardetails", driver.getCardetails());
            }

            if (driver.getStatus() != null) {
                update.set("status", driver.getStatus());
            }


            Driver writeResult = mongoTemplate.findAndModify(query, update, Driver.class);
            if (writeResult != null) {
                response = true;
            }

        return response;
    }

    @Override
    public List<Driver> findDriverDataByFilter(Driver driver)
    {
        Query query = new Query();

        if (driver.getId()!=null) {
            query.addCriteria(Criteria.where("id").is(driver.getId()));
        }

        if (driver.getStatus()!=null) {
            query.addCriteria(Criteria.where("status").is(driver.getStatus()));
        }

        if (driver.getContact_num()!=0) {
            query.addCriteria(Criteria.where("contact_num").is(driver.getContact_num()));
        }

        if (driver.getFull_name()!=null) {
            query.addCriteria(Criteria.where("full_name").is(driver.getFull_name()));
        }

        return mongoTemplate.find(query, Driver.class);

    }

    @Override
    public List<Car> findCarDataByFilter(Car car)
    {
        Query query = new Query();

        if (car.getId()!=null) {
            query.addCriteria(Criteria.where("id").is(car.getId()));
        }

        if (car.getCarOccupied()!=null) {
            query.addCriteria(Criteria.where("carOccupied").is(car.getCarOccupied()));
        }

        if (car.getSeat_cnt()!=0) {
            query.addCriteria(Criteria.where("seat_cnt").is(car.getSeat_cnt()));
        }

        if (car.getRating()!=0) {
            query.addCriteria(Criteria.where("rating").is(car.getRating()));
        }


        if (car.getModel()!=null) {
            query.addCriteria(Criteria.where("model").is(car.getModel()));
        }


        if (car.getManufacturer()!=null) {
            query.addCriteria(Criteria.where("manufacturer").is(car.getManufacturer()));
        }

        if (car.getEngine_type()!=null) {
            query.addCriteria(Criteria.where("engine_type").is(car.getEngine_type()));
        }

        if (car.getLicense_plate()!=null) {
            query.addCriteria(Criteria.where("license_plate").is(car.getLicense_plate()));
        }


        return mongoTemplate.find(query, Car.class);
    }
}
