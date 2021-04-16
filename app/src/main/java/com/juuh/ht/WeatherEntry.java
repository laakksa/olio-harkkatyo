package com.juuh.ht;

public class WeatherEntry {
    private String time;
    private float temp;
    public WeatherEntry(){
        }

    public float getTemp() {
        return temp;
    }

    public String getTime() {
        return time;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
