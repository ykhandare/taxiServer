package com.mytaxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yogesh on 31/3/21.
 */

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        headers = "accept=" + MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class DriverController {

    @Autowired
    private DriverManager driverManager;

    @GetMapping(value = "getDriverData/{Id}")
    public ResponseEntity<BaseResponse> getDriverData(@PathVariable String Id) throws Exception
    {
        return new ResponseEntity<>(driverManager.findDriverData(Id), HttpStatus.OK);
    }

    @PostMapping(value = "createDriver")
    public ResponseEntity<Boolean> createDriverData(@Valid @RequestBody Driver driver) throws Exception {

        return new ResponseEntity<>(driverManager.insertDriverData(driver), HttpStatus.CREATED);
    }

    @PostMapping("/selectCar")
    public ResponseEntity<BaseResponse> selectCarByDriver(@RequestBody CarSelectionRequest carSelectionRequest)
            throws CarAlreadyInUseException
    {

        return new ResponseEntity<>( driverManager.selectCar(carSelectionRequest),HttpStatus.OK);
    }

    @PostMapping("/deSelectCar")
    public ResponseEntity<BaseResponse> deSelectCarByDriver(@RequestBody CarSelectionRequest carSelectionRequest)
            throws Exception
    {

        return new ResponseEntity<>( driverManager.deSelectCar(carSelectionRequest),HttpStatus.OK);
    }

    @PostMapping(value = "updateDriverStatus")
    public ResponseEntity<Boolean> updateDriverData(@Valid @RequestBody Driver driver) throws Exception {

        return new ResponseEntity<>(driverManager.updateDriverStatus(driver), HttpStatus.CREATED);
    }

    @PostMapping("/searchByCharactestics")
    public ResponseEntity<BaseResponse> searchByCharactestics(@RequestBody BaseResponse baseResponse)
            throws Exception
    {

        return new ResponseEntity<>(driverManager.searchCarByCharastics(baseResponse),HttpStatus.OK);
    }
}
