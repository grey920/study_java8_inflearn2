package me.whiteship.java8to11;

public class App {

    public static void main ( String[] args ) {

        Foo foo = new DefaultFoo("grey");

        foo.printName();

        // DefaultFoo 클래스에서 구현하지 않았음에도 정상적으로 출력된다
        // ( 인터페이스의 default 메서드이기 때문에 )
        foo.printNameUpperCase();

        // static메서드 사용
        Foo.printAnything();

    }
}
