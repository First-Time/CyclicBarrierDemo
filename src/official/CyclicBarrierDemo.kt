package official

import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.CyclicBarrier

/**
 * 在子线程中调用await
 *
 * 所有子线程初始化完成后，继续执行barrierAction（如果有的话），然后再并发执行所有子线程的后续任务
 */
class CyclicBarrierDemo(matrix: Array<FloatArray>) {
    private var n = matrix.size
    var done = false
    private val barrierAction = Runnable {
        println("barrier任务执行 ${Date().time}")
    }
    var latch = CountDownLatch(n)
    var barrier = CyclicBarrier(n, barrierAction)
    var threads = ArrayList<Thread>(n)

    inner class Worker(private val row: Int) : Runnable {

        override fun run() {
            while (!done) {
                for (i in 1..100000) {
                    val count = 100 + 1000 + 100000
                }
                println("row $row 开始执行 ${Date().time}")
                if(barrier.await() == 4) {
                    println("线程迭代完成 ${Date().time}")
                }
                println("row $row 执行完成 ${Date().time}")
                latch.countDown()
                done = true
            }
        }
    }

    fun solver() {
        for (i in 1..n) {
            val thread = Thread(Worker(i))
            threads.add(thread)
            thread.start()
        }
        //wait until done
        latch.await()
        for (thread in threads) {
//            thread.join()
            println("所有线程执行完成 ${Date().time}")
        }
        println("======================================")
        barrier.reset()
        done = false
        threads.clear()
        for (i in 1..n) {
            val thread = Thread(Worker(i))
            threads.add(thread)
            thread.start()
        }
        //wait until done
        for (thread in threads) {
            thread.join()
            println("所有线程执行完成 ${Date().time}")
        }
    }

}

fun main() {
    val matrix = arrayOf(
        floatArrayOf(1f, 2f, 3f),
        floatArrayOf(11f, 12f, 13f),
        floatArrayOf(21f, 22f, 23f),
        floatArrayOf(31f, 32f, 33f),
        floatArrayOf(41f, 42f, 43f)
    )
    CyclicBarrierDemo(matrix).solver()
}