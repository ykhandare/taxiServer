package com.mytaxi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by yogesh Khandare on 30/3/21.
 */
@Document(collection = "Driver")
public class Driver {

    @Id
    private  String id;

    private String full_name;

    private long contact_num;

    private Car cardetails;

     private String status;

    private Date dateCreated =new Date();

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public long getContact_num() {
        return contact_num;
    }

    public void setContact_num(long contact_num) {
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
