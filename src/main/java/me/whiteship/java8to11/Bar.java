package me.whiteship.java8to11;

public interface Bar {

    // Bar가 extends로 Foo를 상속받고, Foo가 제공하는 기본 메소드를 제공받고 싶지 않은 경우 추상메서드로 선언. 이러면 Bar를 구현하는 구현체들이 다시 재정의해야 한다
    // 이렇게 안하면 기본 메서드를 사용하게 된다.
//    void printNameUpperCase ();


    /**
     * @implSpec getName()으로 리턴받은 문자열을 대문자로 변환하여 출력한다. from Bar
     */
    default void printNameUpperCase () {
        System.out.println( "BAR" );
    }

}
