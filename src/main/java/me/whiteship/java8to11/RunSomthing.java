package me.whiteship.java8to11;

@FunctionalInterface //컴파일 단계에서 내부를 확인하여 인터페이스를 더 견고하게 관리할 수 있으니 함수형 인터페이스라면 붙이는 게 좋다.
public interface RunSomthing {

        // 추상메서드 ( abstract 키워드 생략함) -> 추상메서드는 오직 하나여야 함.
        int doIt( int number );

        // 새로운 기능! 인터페이스에 인터페이스임에도 static 메서드나 default 키워드로 메서드를 선언할 수 있다.
        static void printName() {
                System.out.println("grey");
        }

        default void printAge() {
                System.out.println("31");
        }
}
