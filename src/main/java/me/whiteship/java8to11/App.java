package me.whiteship.java8to11;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main ( String[] args ) throws InterruptedException {

        /* 사람용 vs 기계용 */
        // 기계적 시간 API
        Instant instant = Instant.now(); // of() => 특정 에폭 시간을 기준으로 만든다
        System.out.println( "instant = " + instant ); // 기준시 UTC ( == 그리니치 GMT )
        System.out.println( instant.atZone( ZoneId.of( "UTC" ) ) ); // 위와 같다

        // UTC를 내 로컬 기준으로 보기
        ZoneId zone = ZoneId.systemDefault();
        System.out.println( "zone = " + zone ); // Asia/Seoul
        ZonedDateTime zonedDateTime = instant.atZone( zone );// 어느 zone을 기준으로 현재 시간을 볼지를 설정한다
        System.out.println( "zonedDateTime = " + zonedDateTime );

        //================
        // 사람용 시간 API
        LocalDate now = LocalDate.now(); // 해당 소스가 돌아가는 서버의 시스템 zone 시간대를 사용한다
        System.out.println( "now = " + now );
        LocalDateTime birthDay = LocalDateTime.of( 1992, 1, 18, 0, 0, 0 );
        // 특정 zone의 시간을 보고싶다면,,
        ZonedDateTime nowInKorea = ZonedDateTime.now( ZoneId.of( "Asia/Seoul" ) );
        System.out.println( "nowInKorea = " + nowInKorea );

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime1 = nowInstant.atZone( ZoneId.of( "Asia/Seoul" ) );
        System.out.println( "zonedDateTime1 = " + zonedDateTime1 );
        /* Instant <-> ZonedDateTime <-> LocalDateTime */

        //===============
        // 기간을 나타내는 두 가지 -> Duration(시간 기반- 기계용), Period(날짜 기반 - 사람용)
        LocalDate today = LocalDate.now();
        LocalDate nextYearBirthday = LocalDate.of( 2023, Month.JANUARY, 18 );

        Period period = Period.between( today, nextYearBirthday );
        System.out.println( period.getDays() );

        Period until = today.until( nextYearBirthday );
        System.out.println( until.get( ChronoUnit.DAYS ));

        // Period는 기간을 연,월,일로 표현하기 때문에 30일이 넘어간 정보는 '월'에 담긴다.
        // 전체 일수를 계산하고 싶다면 ChronoUnit이 제공하는 between을 사용한다
        System.out.println( ChronoUnit.DAYS.between( today, nextYearBirthday ) );


        Instant now1 = Instant.now();
        LocalDate plus = now.plus( 10, ChronoUnit.SECONDS ); // <-  Unsupported unit: Seconds ERROR!
        Duration between = Duration.between( now1, plus );
        System.out.println( "between = " + between.getSeconds() );


    }

}
