package me.whiteship.java8to11;

import me.whiteship.java8to11.annotation.Chicken;
import me.whiteship.java8to11.annotation.ChickenContainer;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("마늘간장")
@Chicken("후라이드")
public class AnnotationApp {
    public static void main ( String[] args ) throws RuntimeException {

        /**
         * 애노테이션 쓰는 방법 두 가지
         */
        // 1. 바로 읽어오는 방법
        Chicken[] chickens = AnnotationApp.class.getAnnotationsByType( Chicken.class );
        Arrays.stream( chickens ).forEach( chicken -> {
            System.out.println( "chicken = " + chicken.value() );
        } );

        // 2. 컨테이너 타입으로 가져오는 방법: 컨테이너는 무조건 있어야 하기 떄문에 컨테이너 타입으로 가져올 수 있다.
        ChickenContainer chickenContainer = AnnotationApp.class.getAnnotation( ChickenContainer.class );
        Arrays.stream( chickenContainer.value() ).forEach( c -> {
            System.out.println( "c.value() = " + c.value() );
        } );
    }


}
