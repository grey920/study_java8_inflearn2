package me.whiteship.java8to11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class App {

    public static void main ( String[] args ) {

        List<String> name = new ArrayList<>();
        name.add( "1. 정겨운" );
        name.add( "2. 정다와" );
        name.add( "3. 정중기" );
        name.add( "4. 이미자" );

        /**
         * forEach: 손쉽게 elements를 순회할 수 있다.
         * 인자로 Functional Interface로 Consumer가 들어온다.
         *
         * 향상된 for문도 똑같지만 Iterator를 안에서 도느냐 밖에서 도느냐의 차이
         */
        name.forEach( System.out::println );

        System.out.println("=================== name forEach() 끝 ======================");


        /**
         * spliterator() : 쪼갤 수 있는 기능을 가진 Iterator
         * 순서가 중요하지 않을 떄 병렬로 처리할 때 유용
         * stream()의 기반이 된다.
         *
         */
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator2 = spliterator.trySplit();// 반으로 쪼갠다
        while ( spliterator.tryAdvance( System.out::println ) ) ; // while문에 본문이 필요없는 경우라 이런식으로 순회를 할 수도 있다.
        System.out.println(":::::::::::::::::::::::::::::::::::::::");
        while ( spliterator2.tryAdvance( System.out::println ) ) ;

        System.out.println("=================== spliterator()  끝 ======================");

        /**
         * 모든 Collection은 하위 인스턴스는 stream()을 가지고 있다 ( stream() 내부 소스에 spliterator()를 사용하고 있음 )
         */
        List<String> list = name.stream().map( String::toUpperCase ).filter( s -> s.contains( "정" ) ).collect( Collectors.toList() );
        System.out.println( list );

        System.out.println("====================== stream() 끝  ===================");



        name.removeIf( s -> s.startsWith( "1." ) );
        name.forEach( System.out::println );

        System.out.println("====================== removeIf() 끝  ===================");


        //        name.sort( String::compareToIgnoreCase );
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort( compareToIgnoreCase.reversed());
        name.forEach( System.out::println );
        System.out.println("====================== Comparator 끝  ===================");

    }
}
