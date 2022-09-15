package me.whiteship.java8to11;

public interface Foo {

    // 필수로 구현해야 함
    void printName();

    // 인스턴스들이 공통적으로 제공해줬으면 하는 기능이라면 -> Default 메서드 사용

    /**
     * @implSpec getName()으로 리턴받은 문자열을 대문자로 변환하여 출력한다.
     */
    default void printNameUpperCase () {
        System.out.println( getName().toUpperCase() );
    }


    /**
     * static 메서드 : 해당 타입 관련 헬퍼 또는 유틸리티 메서드를 제공할 떄 사용
     */
    static void printAnything() {
        System.out.println("Foo");
    }

    String getName();
}
