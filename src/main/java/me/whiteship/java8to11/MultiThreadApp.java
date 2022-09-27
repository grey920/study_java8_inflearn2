package me.whiteship.java8to11;

public class MultiThreadApp {
    public static void main ( String[] args ) throws InterruptedException {

        Thread thread = new Thread( () -> {
                System.out.println( "Thread::: " + Thread.currentThread().getName() );
                try {
                    Thread.sleep( 3000L );
                }
                catch ( InterruptedException e ) {
                    throw new IllegalArgumentException( e );
                }
        });
        thread.start();

        System.out.println( "Hello::: " + Thread.currentThread().getName() );
        thread.join(); // main 쓰레드가 thread가 끝날때까지 기다린다. 기다리지않으면 'thread is finished'는 아무때나 출력된다.
        System.out.println( thread+ " is finished");

    }

}
