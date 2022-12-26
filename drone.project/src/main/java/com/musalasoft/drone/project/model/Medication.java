package com.musalasoft.drone.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.awt.image.BufferedImage;

@Entity
public class Medication {

    private String name;

    private double weight;

    @Column(unique=true)
    private String code;

    private BufferedImage image;

    public Medication(String name, double weight, String code, BufferedImage image) {
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
            throw new IllegalArgumentException();
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
