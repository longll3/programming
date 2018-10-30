package com.sysu.design_pattern.observer_pattern;

import java.util.ArrayList;

/**
 * 天气数据中心，当天气发生变化时会调用measurementChanged方法
 */
public class WeatherData implements Subject {
    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int index = observers.indexOf(o);
        if (index >= 0) observers.remove(index);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer obs =(Observer) observers.get(i);
            obs.update(temperature, humidity, pressure);
        }
    }

    /**
     * 当天气发生变化时会调用measurementChanged方法
     */
    public void measurementChanged() {
        notifyObservers();
    }

    public void setMeasurements(float tmp, float humidity, float pressure) {
        this.humidity = humidity;
        this.temperature = tmp;
        this.pressure = pressure;
        measurementChanged();
    }

}
