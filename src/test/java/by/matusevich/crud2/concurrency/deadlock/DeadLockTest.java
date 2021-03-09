package by.matusevich.crud2.concurrency.deadlock;


public class DeadLockTest {

    public static void main(String[] args) {
        DeadLockTest test = new DeadLockTest();

        final A a = test.new A();
        final A b = test.new A();

        Runnable block1 = () -> {
            synchronized (a) {
                try {
                    System.out.println(Thread.currentThread().getName() + " a");

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("In block 1");
                }
            }
        };

        // Thread-2
        Runnable block2 = () -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " b");

                synchronized (a) {
                    System.out.println("In block 2");
                }
            }
        };

        new Thread(block1).start();
        new Thread(block2).start();
    }

    private class A {
    }
}
