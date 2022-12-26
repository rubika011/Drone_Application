package com.musalasoft.drone.project.model;

import jakarta.persistence.Entity;

import java.awt.image.BufferedImage;

@Entity
public class Medication {

    private String name;
    private double weight;
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
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
