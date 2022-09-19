package me.whiteship.java8to11;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main ( String[] args ) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println(":::::::::::: spring 으로 시작하는 수업");
        springClasses.stream()
                .filter( oc -> oc.getTitle().startsWith( "spring" ) )
                .forEach( oc -> System.out.println( oc.getTitle() ) );

        System.out.println(":::::::::::: close 되지 않은 수업");
//        springClasses.stream()
//                .filter( oc -> ! oc.isClosed() )
//                .forEach( oc -> System.out.println( oc.getTitle() ) );
        /**
         * 메서드 레퍼런스와 static method를 활용해서  Predicate.not( OnlineClass::isClosed ) 이렇게 쓸 수도 있다.
         * ! 이게 바로 안되기 때문에 Predicate.not()을 이용한다
         */
        springClasses.stream()
                .filter( Predicate.not( OnlineClass::isClosed ) )
                .forEach( oc -> System.out.println( oc.getId() ) );

        System.out.println(":::::::::::: 수업 이름만 모아서 스트림 만들기");
        springClasses.stream().map( OnlineClass::getTitle ).forEach( System.out::println );


        /**
         * flatMap. 각자의 리스트 안에 있는 요소들을 풀어서 펼쳐놓는다
         */
        System.out.println(":::::::::::: 두 수업 목록에 들어있는 모든 수업 아이디 출력"); //
        keesunEvents.stream()
                .flatMap( Collection::stream ) // .flatMap( list -> list.stream() )
                .forEach( oc -> System.out.println( oc.getId() ) );

        System.out.println(":::::::::::: 10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate( 10, i -> i + 1 ) // 무한 스트림이라 여기서 forEach로 끝내면 "Non-short-circuit operation consumes infinite stream"이란 경고가 뜬다
                .skip( 10 )
                .limit( 10 )
                .forEach( System.out::println );
        ;

        /**
         * anyMatch()는 boolean값을 리턴하기 때문에 바로 종료가 된다.
         */
        System.out.println(":::::::::::: 자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch( jc -> jc.getTitle().contains( "Test" ) );
        System.out.println( "test = " + test );

        System.out.println(":::::::::::: 스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기");
        List<String> list = springClasses.stream().filter( oc -> oc.getTitle().contains( "spring" ) ).map( OnlineClass::getTitle ).collect( Collectors.toList() );
        System.out.println( "list = " + list );

    }
}
