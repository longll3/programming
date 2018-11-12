### adapter pattern
适配器模式将一个类的接口，转换成客户期望的另一个接口。适配器让原本接口不兼容的类可以合作无间。



 
对象适配器
---
- 使用对象组合，用修改的接口包装被适配器
- 被适配者的任何子类都可以搭配着适配器使用

<img src="http://yuml.me/diagram/nofunky/class/[Client]->[<<Interface>>;Target], [<<Interface>>;Target|request()]^-.-[Adapter|request()], [Adapter]->[Adaptee|specificRequest()]">


类适配器
---
- 使用多继承实现
- 但是java中没有多继承

<img src="http://yuml.me/diagram/nofunky/class/[Client]->[Target|request()], [Target|request()]^-[Adapter|request()], [Adaptee|specificRequest()]^-[Adapter]">





例子
---
for example：
Java中的集合类型collection，如Vector，Stack、Hashtable等。都实现了一个名为elements()的方法。该方法会返回一个枚举Enumeration类。
Enumeration类中有方法hasMoreElements()和nextElement()。

Sun推出更新的集合类是，开始使用了迭代器Iterator接口，该接口的方法有hasNext() next() remove().


今天，我们经常面对遗留代码，这些代码暴露出枚举骑接口，但我们又希望在新的代码中只使用迭代器。就需要一个适配器。

Enumeration类不能实现remove的功能，因此只能在remove方法中抛出一个UnsupportedOperationException()异常。

EnumerationIterator适配器


### 外观模式

提供了一个统一的接口，用来访问子系统中的一群接口。外观定义了一个高层接口，让子系统更容易使用
（对一个接口较为复杂的子系统，提供一个或一些简化接口。）
- 使用组合让外观能够访问子系统中所有的组件
- 然后将子系统的组件整合成一个统一的接口


外观与适配器 两者的关键是 意图不同。

外观和适配器可以包装许多类，但是 外观的意图是 *_简化接口_*，而适配器的意图是 将接口转化为不同的接口。

> **例子**
    
    一个家庭影院子系统中有许多组件，如DVD播放器、投影仪、音响、屏幕等等，每个都有很多接口。
    为这个系统创建一个外观，将这些组件都组合进来，创建一个新的方法，如watchMovie()。
    在该方法中，把看电影需要使用到的组件的方法按顺序委托组件们去处理。


#### 模式目的
装饰者：不改变接口，但加入责任
适配器：讲一个接口转成另一个接口
外  观：让接口更简单