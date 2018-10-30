package com.sysu.design_pattern.observer_pattern;

/**
 * 观察者模式 定义了对象之间的一对多依赖，这样一来，当一个对象改变状态时，它的所有依赖者都会收到通知并更新。
 * Java jdk有自己实现观察者模式 java.util.Observable 和 java.util.Observer，
 */


/**
 * 观察者模式中的主题，负责状态发生改变时通知所有订阅了该主题的观察者（notigyObservers）
 */
public interface Subject {

    public void registerObserver(Observer o); //注册订阅了主题的观察者

    public void removeObserver(Observer o);

    public void notifyObservers();

}
