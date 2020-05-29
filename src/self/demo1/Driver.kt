package self.demo1

import java.util.concurrent.CyclicBarrier

/**
 *
 */
fun main() {
    val barrier = CyclicBarrier(10)
    for (i in 1..10) {
        Thread(Worker(barrier)).start()
    }
    //在此处之后执行的代码与子线程并发执行，不能保证在多个子线程执行完成后执行
    println("doSomethingElse")
    /*for (i in 1..10000) {
        println("alsoDoSomethingElse")
    }*/
    println("onceMoreDoSomethingElse")
}