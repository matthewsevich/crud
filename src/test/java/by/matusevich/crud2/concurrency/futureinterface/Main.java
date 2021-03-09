package by.matusevich.crud2.concurrency.futureinterface;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<String> dataReadFuture = service.submit(new DataReader());
        Future<String> dataProcessFuture = service.submit(new DataProcessor());

        while(!dataReadFuture.isDone()&& !dataProcessFuture.isDone()){
            System.out.println("reading and processing not yet finished");

            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(dataReadFuture.get());
        System.out.println(dataProcessFuture.get());
    }
}
