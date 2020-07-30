package com.example.teachingdemo.design_mode.factory_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class Phone {
    private String brand;
    private String model;
    private String version;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
