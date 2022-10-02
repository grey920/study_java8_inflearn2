package me.whiteship.java8to11.RunnableAndCallable;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * https://codechacha.com/ko/java-callable-vs-runnable/ 에시
 */
public class CallableExample1 {


    static class Mycallable implements Callable<String> {

        /**
         * 메소드 수행 중 Exception을 발생시킬 수 있다
         * @return
         * @throws Exception
         */
        @Override
        public String call () throws Exception {
            return "Called at " + LocalDateTime.now();
        }

    }

    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        Mycallable callable = new Mycallable();
        FutureTask<String> futureTask = new FutureTask<>( callable );
        Thread thread = new Thread( futureTask );
        thread.start();

        // futureTask.get() -> callable.call() 호출됨
        System.out.println( "result : " + futureTask.get() );
    }

}
