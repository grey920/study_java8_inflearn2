package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {

        /* 람다식 직접 구현 */
        UnaryOperator<String> str = ( s ) -> "hi " + s;
        System.out.println(str.apply( "grey" ));

        /* 1. static 메서드 - 객체 타입::static 메소드 */
        UnaryOperator<String> hi = Greeting::hi;

        /* 2. 특정 객체의 instance 메서드 - 객체 레퍼런스::인스턴스 메소드 */
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        /* 3. 생성자 - 객체 타입::new */
        Supplier<Greeting> greetingSupplier = Greeting::new;
        Function<String, Greeting> greetingFunction = Greeting::new;
        Greeting grey = greetingFunction.apply( "grey" );
        System.out.println(grey.getName());

        /* 4. 임의 객체의 instance 메서드 - 객체 타입::인스턴스 메소드 */
        // 특정 타입 불특정 다수인 인스턴스
        String[] names = { "grey", "gyuwoon", "apple"};
//        Arrays.sort( names, ( o1, o2 ) -> 0 );

        // compareToIgnoreCase : 어떤 문자열을 비교해서 int 값을 반환하는 메서드
//        public int compareToIgnoreCase(String str) {
//            return CASE_INSENSITIVE_ORDER.compare(this, str);
//        }
        Arrays.sort( names, String::compareToIgnoreCase );

        System.out.println( "Arrays.toString( names ) = " + Arrays.toString( names ) );

    }

}
