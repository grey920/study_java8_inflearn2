package me.whiteship.java8to11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorApp {
    public static void main ( String[] args ) {

        // static factory method를 사용하여 만들 수 있다
        ExecutorService executorService = Executors.newFixedThreadPool( 2 ); //Thread 하나만 사용

        executorService.submit( getRunnable( "Hello" ) );
        executorService.submit( getRunnable( "Grey" ) );
        executorService.submit( getRunnable( "The" ) );
        executorService.submit( getRunnable( "Java" ) );
        executorService.submit( getRunnable( "Thread" ) );

        /* 위에서 실행만 하므로 계속 돌고 있기 때문에 작업 후에는 명시적으로 shutdown을 해줘야 한다. */
        executorService.shutdown(); // graceful shutdown (현재 진행중인 작업을 끝까지 마치고 죽는다)
//        executorService.shutdownNow(); // 실행중인 작업을 바로 죽인다
    }

    private static Runnable getRunnable ( String msg ) {
        return () -> System.out.println( msg + Thread.currentThread().getName() );
    }
}
