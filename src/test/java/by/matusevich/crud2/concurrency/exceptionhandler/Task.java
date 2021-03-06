package by.matusevich.crud2.concurrency.exceptionhandler;

public class Task implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println(Integer.parseInt("111"));
        System.out.println(Integer.parseInt("112"));
        System.out.println(Integer.parseInt("113"));
        System.out.println(Integer.parseInt("114"));
        System.out.println(Integer.parseInt("11d"));
    }
}
