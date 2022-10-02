package me.whiteship.java8to11.RunnableAndCallable;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample3_lambda {
    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit( () -> "Called at " + LocalDateTime.now() );

        System.out.println( "result : " + future.get() );
    }
}
