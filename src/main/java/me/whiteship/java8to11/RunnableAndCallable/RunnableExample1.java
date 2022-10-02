package me.whiteship.java8to11.RunnableAndCallable;

import java.time.LocalDateTime;

public class RunnableExample1 {

    static class MyRunnable implements Runnable {

        @Override
        public void run () {
            String result = "Called at " + LocalDateTime.now();
            System.out.println( "result = " + result );
        }
    }


    public static void main ( String[] args ) {
//        MyRunnable runnable = new MyRunnable();
//        Thread thread = new Thread( runnable );
//        thread.start();

        /* 람다식으로 Runnable 바로 구현하기 */
        Thread thread = new Thread( () ->{
            System.out.println( Thread.currentThread().getName() );
            System.out.println( "Runnable Called at " + LocalDateTime.now() );
        });
        thread.start();
    }
}
