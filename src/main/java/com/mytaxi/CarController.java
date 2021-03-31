package com.mytaxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yogesh Khandare on 30/3/21.
 */

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        headers = "accept=" + MediaType.APPLICATION_JSON_UTF8_VALUE
)

public class CarController {

    @Autowired
    private CarManager carManager;

    @GetMapping(value = "findCardata/{Id}")
    public ResponseEntity<BaseResponse> getCarData(@PathVariable String Id) throws Exception
    {
        return new ResponseEntity<>(carManager.findCarData(Id), HttpStatus.OK);
    }

    @PostMapping(value = "createCarDetails")
    public ResponseEntity<Boolean> createCarData(@Valid @RequestBody Car car) throws Exception {

        return new ResponseEntity<>(carManager.insertCarData(car), HttpStatus.CREATED);
    }


    @PutMapping(value = "updateCarDetails")
    public ResponseEntity<Boolean> updateCarData(@Valid @RequestBody Car car) throws Exception {

        return new ResponseEntity<>(carManager.updateCarData(car),HttpStatus.OK);
    }


    @DeleteMapping(value = "deleteCarData/{carData}")
    public ResponseEntity<Boolean> deleteCarData(@PathVariable("carData") String carData) throws Exception {
        return new ResponseEntity<>(carManager.deleteCarData(carData),HttpStatus.OK);
    }

}
