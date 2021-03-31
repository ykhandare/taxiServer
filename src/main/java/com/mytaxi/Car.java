package com.mytaxi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

/**
 * Created by yogesh Khandare on 30/3/21.
 */
@Document(collection = "car")

public class Car {

 @Id
 private long id;

 private String license_plate;

 private  int seat_cnt;

 private  float rating;

 private String engine_type;

 private  String model;

 private  String manufacturer;

 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 private ZonedDateTime dateCreated = ZonedDateTime.now();

 private CarHoldingData carHoldingData;


 private String carOccupied;


 public CarHoldingData getCarHoldingData() {
  return carHoldingData;
 }

 public void setCarHoldingData(CarHoldingData carHoldingData) {
  this.carHoldingData = carHoldingData;
 }

 public String getCarOccupied() {
  return carOccupied;
 }

 public void setCarOccupied(String carOccupied) {
  this.carOccupied = carOccupied;
 }



 public ZonedDateTime getDateCreated() {
  return dateCreated;
 }

 public void setDateCreated(ZonedDateTime dateCreated) {
  this.dateCreated = dateCreated;
 }

 public long getId() {
  return id;
 }

 public void setId(long id) {
  this.id = id;
 }

 public String getLicense_plate() {
  return license_plate;
 }

 public void setLicense_plate(String license_plate) {
  this.license_plate = license_plate;
 }

 public int getSeat_cnt() {
  return seat_cnt;
 }

 public void setSeat_cnt(int seat_cnt) {
  this.seat_cnt = seat_cnt;
 }

 public float getRating() {
  return rating;
 }

 public void setRating(float rating) {
  this.rating = rating;
 }

 public String getEngine_type() {
  return engine_type;
 }

 public void setEngine_type(String engine_type) {
  this.engine_type = engine_type;
 }

 public String getModel() {
  return model;
 }

 public void setModel(String model) {
  this.model = model;
 }

 public String getManufacturer() {
  return manufacturer;
 }

 public void setManufacturer(String manufacturer) {
  this.manufacturer = manufacturer;
 }


}
