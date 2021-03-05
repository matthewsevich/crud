package by.matusevich.crud2.concurrency.exceptionhandler;

public class ThreadExceptionCall {

    public static void main(String[] args) {

        try {
            Thread thread = new Thread(new Task());
            thread.setUncaughtExceptionHandler(new ExceptionHandler());
            thread.start();
        } catch (NumberFormatException e) {
            System.out.println("catch");
        }
    }

}
