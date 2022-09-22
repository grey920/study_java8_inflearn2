package me.whiteship.java8to11;

import java.time.Duration;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main ( String[] args ) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
//        springClasses.add(new OnlineClass(2, "spring data jpa", true));
//        springClasses.add(new OnlineClass(3, "spring mvc", false));
//        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        /* stream api에서 Optional을 리턴하는 오퍼레이션들이 있다 -> 종료 오퍼레이션
        => 값이 없을수도, 있을수도 있으니
        * */
        Optional<OnlineClass> optional = springClasses.stream().filter( oc -> oc.getTitle().startsWith( "spring" ) ).findFirst();
        // isPresent()
        boolean present = optional.isPresent();
        System.out.println( "present = " + present );

        // isEmpty()
        boolean empty = optional.isEmpty();
        System.out.println( "empty = " + empty );

        // get()
        // 검사없이 바로 get()을 했을 떄 값이 없으면 NoSuchElementException이 발생한다
        // 가급적이면 get()보다는 밑에 다른 API들을 사용할 것을 권장
        if ( optional.isPresent() ) {
            OnlineClass onlineClass = optional.get();
            System.out.println( onlineClass.getTitle() );
        }


        // ifPresent()
        // if문을 쓰지않고 값이 있을때만 동작을 하도록 만든다.
        optional.ifPresent( oc -> System.out.println( oc.getTitle()) );

        /** 가져와서 계속 작업이 있어서 무조건 OnlineClass로 받아와야 한다?? => or~~~가 붙은 메소드를 사용한다
         * *** 이미 만들어져있는 상수같은 것들은 orElse()를 사용하고, 동작으로 작업을 추가로 해야한다면 orElseGet()이 더 적합하다.  ***
         * 다른 대안이 없다면 orElseThrow()로 에러를 뱉는다.
         */
        // orElse() : 값이 있으나 없으나 무조건 실행한다
//        OnlineClass onlineClass = optional.orElse( createNewClasses() ); //orElse() 인자에는 OnlineClass가 감싸고 있는 인스턴스가 들어온다. 람다나 메소드 참조를 주는게 X

        // orElseGet : 값이 없어야 실행한다.
        OnlineClass onlineClass2 = optional.orElseGet( App::createNewClasses ); // App::createNewClasses 이 함수형인터페이스 구현체이기 때문에 lazy하게 실행될 수 있다.

        // orElseThrow : 값이 없으면 에러를 보낸다
        OnlineClass onlineClass3 = optional.orElseThrow( IllegalArgumentException::new );


        // filter()
        Optional<OnlineClass> onlineClass = optional.filter( OnlineClass::isClosed );
        System.out.println( onlineClass.isPresent());

        // map()
        Optional<Integer> integer = optional.map( OnlineClass::getId );
        System.out.println( "integer = " + integer );

        // flatMap()
        // Optional로 리턴하는 progress의 경우 map으로 꺼내면 양파같이 겹겹이 있다. -> flatMap을 쓰면 타입이 Optional인 경우 한번 까준다
        Optional<Optional<Progress>> progress = optional.map( OnlineClass::getProgress );
        Optional<Progress> progressMap = progress.orElseThrow();
        Optional<Progress> progressFlatmap = optional.flatMap( OnlineClass::getProgress );


    }

    private static OnlineClass createNewClasses () {
        System.out.println("creating new online class ");
        return new OnlineClass( 10, "New class", false );
    }
}
