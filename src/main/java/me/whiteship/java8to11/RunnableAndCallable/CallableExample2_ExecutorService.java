package me.whiteship.java8to11.RunnableAndCallable;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class CallableExample2_ExecutorService {

    static class MyCallable implements Callable<String> {

        @Override
        public String call () throws Exception {
            return "Called at " + LocalDateTime.now();
        }
    }

    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        MyCallable callable = new MyCallable();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit( callable );

        System.out.println("result : " + future.get());
    }
}
