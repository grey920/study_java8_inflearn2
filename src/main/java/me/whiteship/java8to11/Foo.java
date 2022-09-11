package me.whiteship.java8to11;

import java.util.function.*;

public class Foo {

    public static void main ( String[] args ) {

        /* Function<T,R>*/
        Function<Integer, Integer> plus5 = ( i ) -> i + 5;
        Function<Integer, Integer> multiply5 = ( i ) -> i * 5;

        Function<Integer, Integer> plus5AndMultiply5 = plus5.andThen( multiply5 );
        System.out.println( plus5AndMultiply5.apply( 2 ) );

        Function<Integer, Integer> plus5AndcomposeMultiply5 = plus5.compose( multiply5 );
        System.out.println( plus5AndcomposeMultiply5.apply( 2 ) );

        /* UnaryOperator: 입력값과 리턴값이 같은 타입일 경우 */
        UnaryOperator<Integer> subtract2 = ( i ) -> i - 2;
        System.out.println( "subtract2 = " + subtract2.apply( 5 ) );

        /*Predicate*/
        Predicate<Integer> isOdd = ( i ) -> i % 2 != 0;
        System.out.println( isOdd.test( 4 ) );
        // negate(): 반대의 값을 리턴하는 메서드
        Predicate<Integer> negate = isOdd.negate();
        System.out.println( negate.test( 4 ) );

        /*Supplier*/
        Supplier<String> nameSupplier = () -> "I'm grey~";
        System.out.println( nameSupplier.get() );

        /*Consumer*/
        Consumer<String> nameConsumer = ( str ) -> System.out.println( "I'm " + str );
        nameConsumer.accept( "grey!!!!!" );


    }
}
