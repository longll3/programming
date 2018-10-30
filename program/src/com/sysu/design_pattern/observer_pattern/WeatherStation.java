package com.sysu.design_pattern.observer_pattern;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData wd = new WeatherData();

        CurrentConditionsDisplay cd = new CurrentConditionsDisplay(wd);

        wd.setMeasurements(1.0f, 2.0f, 3.0f);
        wd.setMeasurements(2.0f, 3.0f, 4.0f);
        wd.setMeasurements(3.0f, 4.0f, 5.0f);

    }
}
