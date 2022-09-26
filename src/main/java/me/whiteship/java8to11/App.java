package me.whiteship.java8to11;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main ( String[] args ) throws InterruptedException {

        System.out.println( "=============== " );
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
        System.out.println( "===============" );
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
        System.out.println( "===============" );
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


        Instant nowInstantForBetween = Instant.now();
        Instant plus = nowInstantForBetween.plus( 10, ChronoUnit.SECONDS );
        Duration between = Duration.between( nowInstantForBetween, plus );
        System.out.println( "between = " + between.getSeconds() );

        //===============
        System.out.println( "===============" );
        /* formatting : Date -> text  */
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        System.out.println( "localDateTimeNow = " + localDateTimeNow );
        // 원하는 모양으로 출력하고 싶을 때 DateTimeFormatter (문자열로 변환)
        // 미리 정의되어있는 타입이라면 문서 참고 https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern( "MM/dd/yyyy" );
        System.out.println( "Formatted localDateTimeNow = " + localDateTimeNow.format( MMddyyyy ) );

        /* parsing : text -> Date */
        LocalDate parse = LocalDate.parse( "09/27/2022" , MMddyyyy );
        System.out.println( "parse = " + parse );


        //===============
        System.out.println( "===============" );
        /* 레거시 API 지원: Instant로만 변환할 수 있으면 최신 API로 다 바꿀 수 있다 */
        // Date(old) <-> Instant(new)
        Date date = new Date();
        Instant toInstant = date.toInstant(); // date -> instant
        Date toDate = Date.from( toInstant ); // instant -> date

        System.out.println( "toInstant = " + toInstant );
        System.out.println( "toDate = " + toDate );

        // GregorianCalendar(old) <-> ZonedDateTime, LocalDateTime(new)
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone( ZoneId.systemDefault() ); // GregorianCalendar -> ZonedDateTime
        System.out.println( "dateTime = " + dateTime );
        GregorianCalendar from = GregorianCalendar.from( dateTime ); // ZonedDateTime -> GregorianCalendar
        System.out.println( "from = " + from.getTime() );

        // TimeZone(oid) <-> ZoneId(new)
        ZoneId zoneId = TimeZone.getTimeZone( "PST" ).toZoneId(); // TimeZone(oid) -> ZoneId(new)
        System.out.println( "zoneId = " + zoneId );
        TimeZone timeZone = TimeZone.getTimeZone( zoneId ); // ZoneId -> TimeZone
        System.out.println( "timeZone = " + timeZone.getID() );

        System.out.println( "===============" );
        // 연산하기
        LocalDateTime localDateTime = LocalDateTime.now();
        // [Tip!] plus()의 두번째 인자로 TemporalUnit을 넣게 되어있지만, TemporalUnit을 쓰면 더 쓸 수 있는게 없기 때문에 대신 ChronoUnit을 사용하면 편하다
        // [Warning!] localDateTime은 Immutable하기 때문에 반드시 인스턴스를 새로 만든 것으로 사용해야 한다!!! 옛날 API처럼 localDateTime.plus()만 쓰고 끝내면 아무 동작도 하지 않는다.
        LocalDateTime localDatePlus = localDateTime.plus( 10, ChronoUnit.DAYS );
        System.out.println( "localDatePlus = " + localDatePlus );

    }

}
