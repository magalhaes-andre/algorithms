//Suppose we have a class:
//
//public class Foo {
//  public void first() { print("first"); }
//  public void second() { print("second"); }
//  public void third() { print("third"); }
//}
//The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().
//
//Note:
//
//We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seem to imply the ordering. The input format you see is mainly to ensure our tests' comprehensiveness.
//
//
//
//Example 1:
//
//Input: nums = [1,2,3]
//Output: "firstsecondthird"
//Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
//Example 2:
//
//Input: nums = [1,3,2]
//Output: "firstsecondthird"
//Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.
//
//
//Constraints:
//
//nums is a permutation of [1, 2, 3].

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Solutions are ranked on my preference based on readability and apparent extensibility.

//  1.) Synchronized Method : Here, the synchronized keyword is used on the methods themselves to lock the entire object. When a thread enters a synchronized method, it acquires the lock on the object and no other threads can enter any other synchronized method on the same object until the lock is released.
//  2.) Synchronized on Object : In this method, a separate Object is used as a lock instead of the class itself. When a thread enters a synchronized block with the lock object, it acquires the lock and no other threads can enter any other synchronized blocks with the same lock object until the lock is released.
//  3.) Synchronized on Two Objects : Here, two separate objects are used as locks to lock two different sections of the code. This can be useful in some scenarios where a single lock is not sufficient.
//  4.) Semaphore : A Semaphore is a synchronization mechanism that uses a counter to control access to a shared resource. The acquire() method decrements the counter, and the release() method increments it. When the counter is 0, the acquire() method blocks until the counter is greater than 0.
//  5.) Condition Variable : A Condition Variable is a synchronization mechanism that provides a way to wait for a certain condition to be true before proceeding. Here, the await() method releases the lock and blocks the thread until another thread calls the signal() method. The Condition class is used in conjunction with a Lock.
class Solutions {

    // Hardest solution to understand.
    class ReentrantLockSolution {

        private Lock lock;
        private int methodCompleted;
        private Condition firstMethodCompleted;
        private Condition secondMethodCompleted;

        public ReentrantLockSolution() {
            lock = new ReentrantLock();
            methodCompleted = 0;
            firstMethodCompleted = lock.newCondition();
            secondMethodCompleted = lock.newCondition();
        }

        public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            try {
                printFirst.run();
                methodCompleted = 1;
                firstMethodCompleted.signal();
            } finally {
                lock.unlock();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lock.lock();
            try {
                while (methodCompleted != 1) {
                    firstMethodCompleted.await();
                }
                printSecond.run();
                methodCompleted = 2;
                secondMethodCompleted.signal();
            } finally {
                lock.unlock();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            lock.lock();
            try {
                while (methodCompleted != 2) {
                    secondMethodCompleted.await();
                }
                printThird.run();
                methodCompleted = 3;
            } finally {
                lock.unlock();
            }
        }
    }

    // Best in my opinion
    class SemaphoreSolution {
        private Semaphore s1;
        private Semaphore s2;
        private Semaphore s3;

        public SemaphoreSolution() {
            s1 = new Semaphore(0);
            s2 = new Semaphore(0);
            s3 = new Semaphore(0);

            s1.release();
        }

        public void first(Runnable printFirst) throws InterruptedException {
            s1.acquire();
            printFirst.run();
            s2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            s2.acquire();
            printSecond.run();
            s3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            s3.acquire();
            printThird.run();
        }
    }

    class SynchronizedObjectSolution {

        private Lock lock;
        private int methodCompleted;

        public SynchronizedObjectSolution() {
            lock = new ReentrantLock();
            methodCompleted = 0;
        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lock) {
                printFirst.run();
                methodCompleted = 1;
                notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (lock) {
                while (methodCompleted != 1) {
                    wait();
                }
                printSecond.run();
                methodCompleted = 2;
                notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (lock) {
                while (methodCompleted != 2) {
                    wait();
                }
                printThird.run();
                methodCompleted = 3;
                notifyAll();
            }
        }
    }

    // This solution ends up being the same as synchronizing on an object.
    // The main difference ends up being that when not specifying an object, the sync will be done
    // on this object, therefore opening up possibility of denial of service attacks.

    //    As answered by Keith Randall on 12th of September 2012 on stack overflow -> https://stackoverflow.com/questions/12397427/what-is-different-between-method-synchronized-vs-object-synchronized
    //    A synchronized instance method synchronizes on this.
    //    So
    //    public synchronized void increment(){
    //        i = i + 1;
    //    }
    //    is equivalent to
    //    public void increment(){
    //        synchronized (this) {
    //            i = i + 1;
    //        }
    //    }
    class SynchronizedMethodSolution {
        private int completedMethod;

        public SynchronizedMethodSolution() {
            completedMethod = 0;
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            completedMethod = 1;
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while (completedMethod != 1) {
                wait();
            }
            printSecond.run();
            completedMethod = 2;
            notifyAll();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (completedMethod != 2) {
                wait();
            }
            printThird.run();
            completedMethod = 3;
            notifyAll();
        }
    }

    class CountDownLatchSolution {
        private final CountDownLatch firstLatch;
        private final CountDownLatch secondLatch;

        public CountDownLatchSolution() {
            firstLatch = new CountDownLatch(1);
            secondLatch = new CountDownLatch(1);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            firstLatch.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            firstLatch.await();
            printSecond.run();
            secondLatch.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            secondLatch.await();
            printThird.run();
        }
    }

    class VolatileVariableSolution {
        volatile int methodCompleted;

        public VolatileVariableSolution() {
            methodCompleted = 0;
        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            methodCompleted = 1;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (methodCompleted != 1) {
            }
            printSecond.run();
            methodCompleted = 2;

        }

        public void third(Runnable printThird) throws InterruptedException {
            while (methodCompleted != 2) {
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            methodCompleted = 3;
        }
    }


}