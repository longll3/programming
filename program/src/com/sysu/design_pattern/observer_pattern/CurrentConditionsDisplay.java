package com.sysu.design_pattern.observer_pattern;

public class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    private Subject weatherData; // 订阅的主题


    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    public void update(float tmp, float humidity, float pressure) {
        this.humidity = humidity;
        this.temperature = tmp;
        display();

    }

    public void display() {
        System.out.println("Current conditions: " +
                temperature + "F degrees and " + humidity + " % humidity");
    }
}
