package com.musalasoft.drone.project.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Drone {

    @Column(unique=true)
    private String serial_number;

    private String model;

    private double weight_limit;

    private double battery_capacity;

    private String state;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Medication> medications;

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
        if (serial_number.length() > 100) {
            throw new IllegalArgumentException();
        }
        this.serial_number = serial_number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model.equalsIgnoreCase("Lightweight") || model.equalsIgnoreCase("Middleweight") ||
                model.equalsIgnoreCase("Cruiserweight") || model.equalsIgnoreCase("Heavyweight")) {
            this.model = model;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getWeight_limit() {
        return weight_limit;
    }

    public void setWeight_limit(double weight_limit) {
        if (weight_limit > 500) {
            throw new IllegalArgumentException();
        }
        this.weight_limit = weight_limit;
    }

    public double getBattery_capacity() {
        return battery_capacity;
    }

    public void setBattery_capacity(double battery_capacity) {
        if (battery_capacity < 0 || battery_capacity > 100) {
            throw new IllegalArgumentException();
        }
        this.battery_capacity = battery_capacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(state.equalsIgnoreCase("IDLE") || state.equalsIgnoreCase("LOADING") ||
                state.equalsIgnoreCase("DELIVERING") || state.equalsIgnoreCase("DELIVERED") ||
                state.equalsIgnoreCase("RETURNING")) {
            this.state = state;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
