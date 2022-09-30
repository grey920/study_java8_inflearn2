package me.whiteship.java8to11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorApp {
    public static void main ( String[] args ) {

        // static factory method를 사용하여 만들 수 있다
        ExecutorService executorService = Executors.newSingleThreadExecutor(); //Thread 하나만 사용

        // 1. 가장 고전적인 방법
        executorService.execute( new Runnable() {
            @Override
            public void run () {
                System.out.println( "Thread ::: " + Thread.currentThread().getName() );
            }
        } );
    }
}
