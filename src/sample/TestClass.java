package sample;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    static int count = 0;
    AtomicInteger atomicInteger = new AtomicInteger();
    static synchronized void increment() {
        count++;
    }

    static synchronized int get() {
        return count;
    }

}

public class TestClass {
    static int counter = 0;
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService exceutorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            exceutorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        System.out.println(Math.random());
                    } catch (Exception e) {
                        System.out.println();
                    } finally {
                        Counter.increment();
                        counter++;
                    }
                }
            });
        }

        while (true) {
            if (Counter.get() == 10 && counter == 10) {
                System.out.println("Exit");
                break;
            }
        }

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 10, 1000, TimeUnit.MILLISECONDS, queue);


        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        System.out.println(UUID.randomUUID());
                    } catch (Exception e) {
                        System.out.println();
                    } finally {
                        Counter.increment();
                        counter++;
                    }
                }
            });
        }

        while (true) {
            if (Counter.get() == 20 && counter == 20) {
                System.out.println("Exit");
                break;
            }
        }

    }
}
