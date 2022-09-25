package me.whiteship.java8to11;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main ( String[] args ) throws InterruptedException {

        Calendar greyBD = new GregorianCalendar( 1992, Calendar.JANUARY, 18 );
        System.out.println( "greyBD = " + greyBD.getTime() );
        // void 타입을 리턴한다. -> 같은 인스턴스를 바꿔버렸다.
        greyBD.add( Calendar.DAY_OF_YEAR, 1 );
        System.out.println( greyBD.getTime() );

        LocalDate greyBD2 = LocalDate.of( 1992, 1, 18 );
        // 새로운 인스턴스를 반환한다. 즉 기존의 인스턴스는 놔두고 새로운 인스턴스를 조작하여 리턴한다 (Immutable)
        LocalDate plusDays = greyBD2.plusDays( 1 );
        System.out.println( "plusDays = " + plusDays );
    }

}
