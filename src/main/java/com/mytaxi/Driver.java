package com.mytaxi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

/**
 * Created by yogesh Khandare on 30/3/21.
 */
@Document(collection = "Driver")
public class Driver {

    @Id
    private  long id;

    private String full_name;

    private int contact_num;

    private Car cardetails;

     private String status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getContact_num() {
        return contact_num;
    }

    public void setContact_num(int contact_num) {
        this.contact_num = contact_num;
    }

    public Car getCardetails() {
        return cardetails;
    }

    public void setCardetails(Car cardetails) {
        this.cardetails = cardetails;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
