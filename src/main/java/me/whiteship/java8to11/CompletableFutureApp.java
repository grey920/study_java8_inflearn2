package me.whiteship.java8to11;


import java.util.concurrent.*;

/**
 * 쓰레드풀을 만들지 않았는데 어떻게 별도의 쓰레드에서 동작을 했을까??
 * -> Java7에 들어온 ForkJoinPool 때문에 가능!
 * - ForkJoinPool: Executor를 구현한 구현체 중 하나. dequeue를 사용해서 자기 쓰레드에 할 일이 없으면 쓰레드가 직접 dequeue해서 할 일을 가져와 처리하는 방식.
 *  작업 단위를 자기가 파생시킨 세부 sub task가 있다면, 그 sub task들을 잘게 쪼개서 다른 쓰레드로 분산 시켜 작업을 처리하고 모아서 그 결과값을 도출해낸다
 *
 *  즉, 별다른 Executor를 사용하지 않아도 내부적으로 ForkJoinPool에 있는 commonPool을 사용한다.
 *  하지만 원한다면 만들어서 줄 수도 있다.
 */
public class CompletableFutureApp {


    public static void main ( String[] args ) throws ExecutionException, InterruptedException {

        // 1. new 로 CompletableFuture 생성 ( 이제 Executors 쓰지 않아도 됨 )
        CompletableFuture<String> future1 = new CompletableFuture<>();

        // 기본값을 설정
        future1.complete( "future1" );

        // (기본값 설정과 동시에) 작업을 끝냄
        System.out.println(future1.get()) ;


        // 2. static factory method를 써서 생성할 수도 있다
        CompletableFuture<String> future2 = CompletableFuture.completedFuture( "future2" );
        System.out.println( future2.get() );


        // 3. 비동기로 작업 실행하기: 실제로 어떤 작업을 하고싶은 경우 리턴이 있는 경우와 없는 경우로 나눌 수 있다.
        // 3-1. 리턴이 없는 경우 -> runAsync()
        // 리턴값이 없어서 Generic이 Void가 된다.
        CompletableFuture<Void> future3 = CompletableFuture.runAsync( () -> {
            System.out.println( "future3 Hello " + Thread.currentThread().getName() );
        } );
        future3.get(); // get() 또는 join()을 해야 뭔가 일이 벌어진다.
        future3.join(); // join() -> 안에 Exception이 발생하는 상황에서 uncheckedException으로 던져주기 때문에 에러처리를 안해줘도 된다. ( get은 예외처리 해야함 )

        // 3-2. 리턴타입이 있는 경우 -> supplyAsync()
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync( () -> {
            System.out.println( "Hello4 " + Thread.currentThread().getName() );
            return "future4 ";
        } );
        System.out.println(future4.get()); // future4.get()을 "할 때" 위의 supplyAsync()가 수행된다


        /* 4. 콜백 제공하기
        * [콜백 종류]
        * - thenApply() : 받은 결과값을 다른 타입으로 변경한다
        * - thenAccept() : 리턴값을 또 다른 작업을 처리한다 (리턴X)
        * - thenRun() : 리턴값을 받지 않고 또 다른 작업을 처리한다
        * 그래도 get()은 호출해야 한다
        * (중요!) 기존 Java5에 Future는 get() 이전에 콜백을 정의해놓는게 불가능했는데 CompletableFuture에서는 .thenApply()로 get()전에 미리 콜백을 써놓는게 가능해졌다!!!
         */
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync( () -> {
            System.out.println( "Hello5 " + Thread.currentThread().getName() );
            return "future5 ";
        } ).thenApply( (s) -> {
            System.out.println( "Hello5 thenApply " + Thread.currentThread().getName() );
            return s.toUpperCase();
        } );
        System.out.println(future5.get()); // !!get()으로 호출해야한다! 안하면 아무일도 안일어난다 (근데 위에 supplyAsyc()인 future3이나 4를 열어놓으면 future5에서 get()을 안해도 실행된다.. 뭘까

        // 리턴이 없는 콜백인 경우 -> Consumer 타입을 받는 .thenAccept()를 사용
        CompletableFuture<Void> future6 = CompletableFuture.supplyAsync( () -> {
            System.out.println( "Hello6 " + Thread.currentThread().getName() );
            return "future6 ";
        } ).thenAccept( (s) -> {
            System.out.println( "Hello6 thenAccept " + Thread.currentThread().getName() );
            System.out.println( s.toUpperCase() );
        } );
        future6.get();

        // 리턴받을 필요없이 뭔가를 하기만 하면 된다! -> Runnable을 받는 .thenRun
        CompletableFuture<Void> future7 = CompletableFuture.supplyAsync( () -> {
            System.out.println( "Hello7 " + Thread.currentThread().getName() );
            return "future7 ";
        } ).thenRun( () -> {
            System.out.println( "Hello7 thenRun " + Thread.currentThread().getName() );
        });
        future7.get();


        // Executors 사용해서 쓰레드풀 직접 만들어서 주기
        // 위의 예처럼 ForkJoinPool의 commonPool이 아니라 pool-1-thread-1이 출력된다
        ExecutorService executorService = Executors.newFixedThreadPool( 4 );
        // 두번째 인자로 ExecutorService를 줄 수 있다
        CompletableFuture<Void> future8 = CompletableFuture.supplyAsync( () -> {
            System.out.println( "Hello8 " + Thread.currentThread().getName() );
            return "future8";
        }, executorService ).thenRunAsync( () -> {
            System.out.println( "Hello8 executorService thenRunAsync " + Thread.currentThread().getName() );
        } , executorService ); // thenRunAsync 등의 콜백을 다른 쓰레드에서 수행하고 싶다면 두번째 인자에 ES를 준다
        future8.get();
        executorService.shutdown();


        System.out.println(":::::::::::::::::::: 조합하기 ::::::::::::::::::");

        /* 5. 조합하기
        * */
        CompletableFuture<String> hello = CompletableFuture.supplyAsync( () -> {
            System.out.println( "Hello 9 " + Thread.currentThread().getName() );
            return "Hello";
        } );

        // 이전의 경우, hello.get()을 하고 기다렸다가 world.get()을 해야했다.
//        hello.get();
//        world.get();

        // thenCompose() : 이 뒤에 추가적인 작업을 이어서 할 수 있다 ( 서로 의존성, 연관관계가 있는 경우 사용. 즉 순서가 정해져있는 경우 )
        CompletableFuture<String> future9_1 = hello.thenCompose( CompletableFutureApp::getWorld );// hello의 결과를 받아서
        System.out.println( "future9_1 thenCompose = " + future9_1.get() );



        // thenCombine() : 서로 연관관계가 없는 경우 비동기적인 작업을 동시에 처리하는 방법
        // ( 예, A는 애플 주식 정보 조회하고 B는 삼성 주식 조회할 때 따로 가져오는 경우 -> A 조회 끝날때까지 B가 기다릴 필요없이 A와 B 모두 던져놓고 둘 다 결과가 왔을 때 뭔가를 하고싶은 경우 사용 )
        CompletableFuture<String> future9_2 = hello.thenCombine( getWorld("Hello"), ( h, w ) -> h + " " + w );// BiFunction을 사용
        System.out.println( "future9_2 thenCombine = " + future9_2.get() );


        // allOf() : 2개 이상일 때 여러 task들을 다 합쳐서 실행
        // allOf에 넘어간 일들이 다 끝났을 때 .thenApply나 thenAccept 등 추가적인 콜백을 수행할 수 있다.
        // [주의!!] allOf의 인자가 같은 타입이라는 보장도 없고, 수행 중에 에러가 날 수도 있다. 아래 예시는 인자의 타입이 다른 경우라 null이 출력된다
        CompletableFuture<Void> future9_3 = CompletableFuture.allOf( hello, getIntegerWorld( 1 ) ).thenAccept( System.out::println );
        System.out.println( "future9_3 = " + future9_3.get() );


    }

    private static CompletableFuture<String> getWorld ( String msg ) {
        return CompletableFuture.supplyAsync( () -> {
            System.out.println( msg + "World 9 " + Thread.currentThread().getName() );
            return msg + " World";
        } );
    }

    private static CompletableFuture<Integer> getIntegerWorld ( Integer integer ) {
        return CompletableFuture.supplyAsync( () -> {
            System.out.println( integer + "World " + Thread.currentThread().getName() );
            return integer;
        } );
    }

}
