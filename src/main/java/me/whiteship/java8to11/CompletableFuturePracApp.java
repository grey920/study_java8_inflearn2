package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFuturePracApp {
    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync( () -> {
            System.out.println( "Hello " + Thread.currentThread().getName() );
            return "Hello";
        } );

        CompletableFuture<String> world = CompletableFuture.supplyAsync( () -> {
            System.out.println( "World " + Thread.currentThread().getName() );
            return "World";
        } );

        // anyOf() : 빨리 끝나는 것 결과 아무거나 하나 받아서 실행.
        CompletableFuture<Void> future = CompletableFuture.anyOf( hello, world ).thenAccept( System.out::println );
        future.get();



        // ===============>Collection을 만들어 allOf()의 리턴값을 CompletableFuture<Void> 대신 제대로 받기

        // 1. CompletableFuture 작업 여러개를 List로 담는다
        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        // 2. List<CompletableFuture<String>>를  CompletableFuture<String>[] array로 바꾼다.
        CompletableFuture<String>[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);
        // 3. array에 담긴 CompletableFuture들이 모두 끝나면 결과값들을 모아 List로 만든다.
        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join) // join()은 Unchecked Exception, get()은 Checked Exception 발생
                        .collect( Collectors.toList()));

        // results.get()을 하면 결과값이 모인 List가 나온다
        results.get().forEach(System.out::println);


        // =============> 개선하기 (https://inflearn.com/questions/651227 )
        // CompletableFuture 작업들이 끝나면 바로 thenApply를 실행하여 각 작업들의 결과값을 List로 만든다.
        CompletableFuture<List<String>> advancedResults = CompletableFuture.allOf( hello, world )
                                                                            .thenApply( v -> Arrays.asList( hello.join(), world.join() ) );
        advancedResults.get().forEach( System.out::println );


    }

}
