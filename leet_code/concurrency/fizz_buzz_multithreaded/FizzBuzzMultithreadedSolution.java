import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzzMultithreadedSolution {

    class FizzBuzz {
        private int n;
        private int currentNumber;
        private Object lock;
        public FizzBuzz(int n) {
            this.n = n;
            currentNumber = 1;
            lock = new Object();
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            synchronized (lock) {
                while (currentNumber <= n) {
                    if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                        printFizz.run();
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            synchronized (lock) {
                while (currentNumber <= n) {
                    if (currentNumber % 3 != 0 && currentNumber % 5 == 0) {
                        printBuzz.run();
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            synchronized (lock) {
                while (currentNumber <= n) {
                    if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                        printFizzBuzz.run();
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            synchronized (lock) {
                while (currentNumber <= n) {
                    if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                        printNumber.accept(currentNumber);
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                }
            }
        }
    }
}
