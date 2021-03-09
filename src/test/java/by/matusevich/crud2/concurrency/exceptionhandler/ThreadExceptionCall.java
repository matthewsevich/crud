package by.matusevich.crud2.concurrency.exceptionhandler;

public class ThreadExceptionCall {

    public static void main(String[] args) {

            Thread thread = new Thread(new Task());
            thread.setUncaughtExceptionHandler(new ExceptionHandler());
            thread.start();
    }

}
