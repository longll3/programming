### 命令模式

将"请求"封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。

命令模式也支持可撤销的操作。

- 一个命令对象通过在特定接受者上绑定一组动作来封装一个请求
    * 命令对象将动作和接受者包进对象中。
    * 这个命令对象只暴露一个execute（）方法。
    * 当次方法调用时，接受者就会进行这些动作
    * 从外面看来，其他对象不知道究竟是哪个接受者进行了哪些动作，只知道如果调用execute（）方法，请求的目的就能达到。
    
   
   
   <img src="http://yuml.me/diagram/nofunky/class/[Client]->[Receiver], [Client]->[ConcreteCommand], [Invoker|+setCommand()]->[<<Command>>], [<<Command>>]^-.-[ConcreteCommand|execute();undo()], [ConcreteCommand]->[Receiver], [Receiver|+action()]" >

 
---
宏命令

我的理解就是一个包含多个命令对象的超级命令对象，从而可以实现 一次 调用 来自多个不同接受者的不同命令的 复杂命令。
 

#### 命令模式的更多用途

1. 日志请求
    - 在执行命令的时候，将命令的执行顺序存储在日志中，一旦系统死机，将命令对象重新加载，并成批次的依次调用这些对象的execute方法。
    - 更详细的
        - 将在检查点checkpoint之后的所有操作记录下来， 如果系统出状况，从检查点开始应用这些操作。
        - 如电子表格应用，错误恢复方式是将电子表格的操作记录在日志中，而不是一有变化就将整个电子表格记录下来。
    - 可被扩展应用到事务transaction处理中。
1. 线程池
1. 工作队列等
    - 在工作队列的一端添加命令对象，另一端则是线程，从队列中取出一个命令对象，调用它的execute方法。
    - 工作队列类和进行计算的对象（接受者对象）之间是完全解耦的。

    
    

