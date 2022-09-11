package me.whiteship.java8to11;

import java.util.function.*;

public class Foo {
    static int classBaseNum = 20; //클래스 변수
    public int instanceBaseNum = 10;

    public static void main ( String[] args ) {

        Foo foo = new Foo();
        foo.run();

    }

    private void run () {

        int baseNum = 10;

        IntConsumer printInt = ( i ) -> {
            System.out.println( i + instanceBaseNum );
            instanceBaseNum++;
            classBaseNum++;
//            baseNum++; // <- [ERR] 컴파일 에러!!!

            System.out.println("instanceBaseNum : " + instanceBaseNum );
            System.out.println("classBaseNum : " + classBaseNum );
        };

        printInt.accept( 5 );
    }




    private void run2 () {

        int baseNum = 10;

        /* 로컬 클래스 */
        class LocalClass{
            void printBaseNum( int baseNum ){
                System.out.println( baseNum );
            }
        }

        /* 익명 클래스 */
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept ( Integer integer ) {

                System.out.println( baseNum );
            }
        };

        IntConsumer printInt = ( i ) -> {
            // 여기서 참조하는 로컬 변수인 baseNum은 캡쳐가 된다. (변수 캡쳐 variable capture)
//            int baseNum = 20;
            System.out.println( i + baseNum );
        };

        integerConsumer.accept( 5 );
        printInt.accept( 5 );
    }

}
