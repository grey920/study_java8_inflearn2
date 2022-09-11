package me.whiteship.java8to11;

public class Foo {

    public static void main ( String[] args ) {

        RunSomthing runSomthing = new RunSomthing() {
            int baseNumber = 10;

            @Override
            public int doIt ( int number ) {
                // 문법적으로는 가능하지만 순수한 함수라고 할 수 없다.
                baseNumber++;
                return number + baseNumber;
            }
        };


    }
}
