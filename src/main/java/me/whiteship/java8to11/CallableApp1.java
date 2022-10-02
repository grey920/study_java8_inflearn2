package me.whiteship.java8to11;

import java.util.concurrent.*;

public class CallableApp1 {

    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        // Single Thread로 ExecutorService 생성
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable 생성(정의)
        Callable<String> hello = () -> {
            Thread.sleep( 2000L );
            return "Hello";
        };

        // Callable 사용 ( Runnable과 같다 )
        Future<String> helloFuture = executorService.submit( hello );
        System.out.println( helloFuture.isDone() );
        System.out.println("Started!!!!!!!!!!!!!!!!");


        // submit이 만들어주는 값을 받아옴
        // [주의!] 값을 받을때까지 기다린다 -> 그냥 무작정 기다리기만 해야하나?
        // ==> 상태를 알 수 있다 ( isDone()으로 체크 )
//        helloFuture.get();

        // 작업 취소하기
        // true : 현재 진행중인 작업을 interrupt하면서 종료시킨다.
        // false: 기다린다. 하지만 일단 cancel을 호출하면 후에 get()으로 값을 가져올 수 없다. (isDone을 무족건 true)
        helloFuture.cancel( false );

        System.out.println( helloFuture.isDone() );

        //[ERROR] 취소한 작업에 값을 가져오라고 하면 java.util.concurrent.CancellationException 에러가 발생함.
        helloFuture.get();
        System.out.println("END!!!!!!!!!!!!!!!!!!!!");
        // task들 종료
        executorService.shutdown();
    }
}
