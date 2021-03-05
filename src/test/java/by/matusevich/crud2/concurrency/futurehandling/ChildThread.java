package by.matusevich.crud2.concurrency.futurehandling;

import java.util.concurrent.Callable;

public class ChildThread implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("do smth 1");
        exceptionMethod();
        System.out.println("do smth 2");
        return null;
    }

    private void exceptionMethod() {
        throw new RuntimeException("childthread exception");
    }
}
