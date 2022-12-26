package com.musalasoft.drone.project.model;

import jakarta.persistence.Entity;

@Entity
public class Drone {

    private String serial_number;
    private String model;
    private double weight_limit;

    public Drone(String serial_number, String model, double weight_limit, double battery_capacity, String state) {
        this.serial_number = serial_number;
        this.model = model;
        this.weight_limit = weight_limit;
        this.battery_capacity = battery_capacity;
        this.state = state;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getWeight_limit() {
        return weight_limit;
    }

    public void setWeight_limit(double weight_limit) {
        this.weight_limit = weight_limit;
    }

    public double getBattery_capacity() {
        return battery_capacity;
    }

    public void setBattery_capacity(double battery_capacity) {
        this.battery_capacity = battery_capacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private double battery_capacity;
    private String state;

}
