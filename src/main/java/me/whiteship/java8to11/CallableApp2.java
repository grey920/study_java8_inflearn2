package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Callable에 여러 작업들을 동시에 뭉텅이로 주기
 */
public class CallableApp2 {

    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        // Single Thread로 ExecutorService 생성
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool( 1 );

        // 여러개의 작업 정의
        Callable<String> hello = () -> {
//            Thread.sleep( 4000L );
            return "Hello";
        };

        Callable<String> java = () -> {
//            Thread.sleep( 2000L );
            return "Java";
        };

        Callable<String> grey = () -> {
//            Thread.sleep( 2000L );
            return "Grey";
        };



        // Collection을 만들어 한꺼번에 보내기
        // invokeAll()은 모든 작업이 끝날때까지 기다렸다가 한꺼번에 결과물을 보내준다
//        List<Future<String>> futures = executorService.invokeAll( Arrays.asList( hello, java, grey ) );
//        for( Future<String> f : futures ){
//            System.out.println( f.get() );
//        }

        /*
        * 와우, 이게 싱글 쓰레드일때와 멀티 쓰레드일 경우가 다른 것 같다.
        * */
        String s = executorService.invokeAny( Arrays.asList( hello, java, grey ) );
        System.out.println( "s = " + s );

        // task들 종료
        executorService.shutdown();
    }
}
