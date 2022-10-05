package me.whiteship.java8to11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    }

}
