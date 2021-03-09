package by.matusevich.crud2.concurrency.futureinterface;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class DataProcessor implements Callable {
    @Override
    public String call() throws Exception {
        System.out.println("processing data");
        TimeUnit.SECONDS.sleep(5);
        return "Data is processed";
    }
}
