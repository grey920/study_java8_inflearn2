package me.whiteship.java8to11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main ( String[] args ) {

        List<String> names = new ArrayList<>();
        names.add( "정겨운" );
        names.add( "정다와" );
        names.add( "정중기" );
        names.add( "이미자" );

        /**
         * 여기서 중개 오퍼레이션인 map() 내의 출력문은 출력되지 않는다.
         * 중개 오퍼레이션은 종료 오퍼레이 오기 전까지는 실행하지 않기 때문이다.
         */
//        Stream<String> stringStream = names.stream()
//                .map( s -> {
//                    System.out.println(s);
//                    return s.toUpperCase();
//                } );
//
//        System.out.println("===========================");

        /**
         * parallelStream()을 사용하여 병렬 처리. ( ForkJoinPool을 써서 다른 쓰레드 사용 )
         */
        List<String> collect = names.stream().map( s -> {
            System.out.println( s + " " + Thread.currentThread().getName() );
            return s.toUpperCase();
        } ).collect( Collectors.toList() );

        collect.forEach( System.out::println );

    }
}
