package com.sysu.design_pattern;

public class Singleton {

    private static Singleton instance;

    //关键代码：构造函数私有
    private Singleton() {}

    //懒汉式，线程不安全：不支持多线程
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    //

    /**懒汉式，线程安全：支持多线程,效率很低，99% 情况下不需要同步
     * 优点：第一次调用才初始化，避免内存浪费
     * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
     * @return
     */
    public static synchronized Singleton getInstanceThreadSafe() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    /******以下都线程安全***********
     * 饿汉式，推荐使用
     * 优点：没有加锁，执行效率会提高。
     * 缺点：类加载时就初始化，浪费内存。
     */
    private static Singleton instanceForHurgyMode = new Singleton();
    public static Singleton getInstanceHugryMode() {
        return instanceForHurgyMode;
    }

    /**
     * 双检锁/双重校验锁（DCL，即 double-checked locking）
     * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     */
    private volatile static Singleton singleton; //volatile关键字是得不会被编译器重排列指令优化
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) { //不对整个方法都加锁，只对获取实例部分加锁
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /**
     * 登记式/静态内部类
     * 这种方式能达到双检锁方式一样的功效，但实现更简单。
     * 对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
     * 这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程，它跟第 3 种方式不同的是：第 3 种方式只要 Singleton 类被装载了，
     * 那么 instance 就会被实例化（没有达到 lazy loading 效果），而这种方式是 Singleton 类被装载了，instance 不一定被初始化。
     * 因为 SingletonHolder 类没有被主动使用，只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance。
     * 想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，
     * 另外一方面，又不希望在 Singleton 类加载时就实例化，因为不能确保 Singleton 类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化 instance 显然是不合适的。
     * 这个时候，这种方式相比第 3 种方式就显得很合理。
     */

    //新增一个类
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static final Singleton getInstanceLoginMode() { //final保证不可被重写
        return SingletonHolder.INSTANCE; //调用这个方法时，SingletonHolder才会被load，从而实例化一个Singleton对象。这就达到了延迟装载的目的
    }

    /**
     * 枚举
     * 这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
     * 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。
     */
    public enum SingletonEnum {
        INSTANCE;
        public void whateverMethod() {
        }
    }
}
/**
 * 一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。
 * 只有在要明确实现 lazy loading 效果时，才会使用第 5 种登记方式。
 * 如果涉及到反序列化创建对象时，可以尝试使用第 6 种枚举方式。
 * 如果有其他特殊的需求，可以考虑使用第 4 种双检锁
 */
