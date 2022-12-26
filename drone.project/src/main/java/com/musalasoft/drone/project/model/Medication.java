package com.musalasoft.drone.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.awt.*;
import java.awt.image.BufferedImage;

@Entity
@Table(name="medication")
public class Medication {

    private String name;

    private double weight;

    @Id
    @Column(unique=true)
    private String code;

    private byte[] image;

    public Medication() {
    }

    public Medication(String name, double weight, String code, byte[] image) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches("[a-zA-Z0-9-_]*")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Letters, Numbers, '-' and '_' are only allowed for name");
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be less than 0");
        }
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.matches("[A-Z0-9_]*")) {
            this.code = code;
        } else {
            throw new IllegalArgumentException("Capital Letters, Numbers and '_' are only allowed for code");
        }
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
