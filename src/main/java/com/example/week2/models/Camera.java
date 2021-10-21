package com.example.week2.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Camera {
    private int cameraId;
    private String make;
    private String model;
//    private ArrayList<String> lenses;
    private double megaPixels;
    private double price;
    private boolean digital;
    private boolean mirrorless;
    private int unitsSold;

//    public Camera(String make, String model, double megaPixels, double price, boolean digital, boolean mirrorless) {
//        this(make,model,megaPixels,price,digital,mirrorless, 0);
//        cameraId=0;
//    }

    public Camera(String make, String model, double megaPixels, double price, boolean digital, boolean mirrorless) {
        setMake(make);
        setModel(model);
        setMegaPixels(megaPixels);
        setPrice(price);
        setDigital(digital);
        setMirrorless(mirrorless);
        setUnitsSold(0);
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        if (cameraId>0)
            this.cameraId = cameraId;
        else
            throw new IllegalArgumentException("cameraId must be greater than 0");
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        List<String> validManufacturers = Arrays.asList("Canon","Nikon","Sony","Mitsubishi","FujiFilm");
        if (validManufacturers.contains(make))
            this.make = make;
        else
            throw new IllegalArgumentException(String.format("%s is invalid, choose from %s",make,
                    validManufacturers));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    //    public ArrayList<String> getLenses() {
//        return lenses;
//    }
//
//    public void setLenses(ArrayList<String> lenses) {
//        this.lenses = lenses;
//    }

    public double getMegaPixels() {
        return megaPixels;
    }

    public void setMegaPixels(double megaPixels) {
        this.megaPixels = megaPixels;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public boolean isMirrorless() {
        return mirrorless;
    }

    public void setMirrorless(boolean mirrorless) {
        this.mirrorless = mirrorless;
    }

    public String toString()
    {
//        return make + " " + model + " $" + price;
        return String.format("%d-%s %s $%.2f", cameraId, make, model, price);
    }
}
