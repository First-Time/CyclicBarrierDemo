package self.demo1

import java.util.concurrent.CyclicBarrier
import java.util.concurrent.TimeUnit

/**
 * CyclicBarrier可以重复使用
 */
class Worker : Runnable {
    private var barrier: CyclicBarrier

    constructor(barrier: CyclicBarrier) {
        this.barrier = barrier
    }

    override fun run() {
        println("子线程doSomething")
        barrier.await() //保证多个子线程在此处之前的代码都执行完成后，多个子线程同时继续向后执行
        doWork()

        TimeUnit.SECONDS.sleep(1)
        println("子线程doSomethingElse")
        barrier.await()
        doOtherWork()
    }

    private fun doWork() {
        println("doWork()方法被调用")
    }

    private fun doOtherWork() {
        println("doOtherWork()方法被调用")
    }
}