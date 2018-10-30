package com.sysu.design_pattern.observer_pattern;

import javafx.beans.Observable;

/**
 * 观察者模式中的观察者
 */
public interface Observer {

    /**
     * 收到来自主题的状态改变的消息的处理方式
     * 这是一个气象站的例子
     * @param tmp 温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    public void update(float tmp, float humidity, float pressure);
}




