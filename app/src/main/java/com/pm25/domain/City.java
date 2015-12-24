package com.pm25.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by root on 12/24/15.
 */
public class City implements Serializable{


    @SerializedName("area")
    private String area;
    @SerializedName("quality")
    private String quality;
    @SerializedName("time_point")
    private String time_point;
    @SerializedName("aqi")
    private int aqi;
    @SerializedName("pm2_5")
    private int pm2_5;
    @SerializedName("pm10")
    private int pm10;
    @SerializedName("co")
    private double co;
    @SerializedName("no2")
    private int no2;
    @SerializedName("o3")
    private int o3;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getTime_point() {
        return time_point;
    }

    public void setTime_point(String time_point) {
        this.time_point = time_point;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public int getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(int pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public int getPm10() {
        return pm10;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public double getCo() {
        return co;
    }

    public void setCo(int co) {
        this.co = co;
    }

    public int getNo2() {
        return no2;
    }

    public void setNo2(int no2) {
        this.no2 = no2;
    }

    public int getO3() {
        return o3;
    }

    public void setO3(int o3) {
        this.o3 = o3;
    }

}