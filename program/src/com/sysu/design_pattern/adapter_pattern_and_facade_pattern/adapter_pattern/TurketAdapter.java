package com.sysu.design_pattern.adapter_pattern_and_facade_pattern.adapter_pattern;


/**
 * 假设缺鸭子对象，想有一些火鸡对象来冒充。
 * 因为火鸡的接口不同，所以不能公然拿来用
 *
 * 那么就需要一个 适配器
 */

//首先需要实现想转换成的接口，也就是客户所期望看到的接口Duck
public class TurketAdapter implements Duck {
    //适配器与被适配者 组合
    Turkey turkey;

    //取得要被适配的对象引用
    public TurketAdapter(Turkey turkey) {
        this.turkey = turkey;
    }


    /**
     * 所有请求委托给被适配类
     */

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
