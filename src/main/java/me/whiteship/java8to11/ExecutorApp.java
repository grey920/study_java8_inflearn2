package me.whiteship.java8to11;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.*;

public class ExecutorApp {
    public static void main ( String[] args ) throws ExecutionException, InterruptedException {

        // static factory method를 사용하여 만들 수 있다
        ExecutorService executorService = Executors.newFixedThreadPool( 2 );

//        executorService.submit( getRunnable( "Hello" ) );
//        executorService.submit( getRunnable( "Grey" ) );
//        executorService.submit( getRunnable( "The" ) );
//        executorService.submit( getRunnable( "Java" ) );
//        executorService.submit( getRunnable( "Thread" ) );

        String s = executorService.invokeAny( Arrays.asList( getCallable( "Hello" ), getCallable( "Grey" ), getCallable( "The" ), getCallable( "Java" ), getCallable( "Thread" ) ) );
        System.out.println( "s = " + s );


        /* 2. ScheduledExecutorService */
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // 스케쥴을 받아서 3초 기다린 후 실행
//        executorService.schedule( getRunnable("Hello"), 3, TimeUnit.SECONDS );

        // 반복 작업을 하는 경우 ( 1초 기다렸다가 2초에 한 번씩 출력)
        // shutdown()을 하면 안에 task가 InterruptedException을 받아 그 때 종료가 되어버리기 때문에 shutdown()을 주석해야 확인 가능.
//        executorService.scheduleAtFixedRate( getRunnable("Hello"), 1, 2,  TimeUnit.SECONDS );

        /* 위에서 실행만 하므로 계속 돌고 있기 때문에 작업 후에는 명시적으로 shutdown을 해줘야 한다. */
        executorService.shutdown(); // graceful shutdown (현재 진행중인 작업을 끝까지 마치고 죽는다)
//        executorService.shutdownNow(); // 실행중인 작업을 바로 죽인다
    }

    /**
     * Runnable은 void이다. 리턴을 받으려면 Callable을 사용해야 한다.
     * @param msg
     * @return
     */
    private static Runnable getRunnable ( String msg ) {
        return () -> System.out.println( msg + " ::: " + Thread.currentThread().getName() + " ::: " + LocalDateTime.now() );
    }

    private static Callable<String> getCallable ( String msg ) {
        return () -> msg + " ::: " + Thread.currentThread().getName() + " ::: " + LocalDateTime.now();
    }
}
