package me.whiteship.java8to11;

public class DefaultFoo implements  Foo {

    public String name;

    public DefaultFoo ( String name ) {
        this.name = name;
    }


    @Override
    public void printName () {
        System.out.println( this.name );
    }

    /**
     * @implSpec getName()으로 리턴받은 문자열을 대문자로 변환하여 출력한다.
     */
    @Override
    public void printNameUpperCase () {
        Foo.super.printNameUpperCase(); // 여기서 Bar로 할지 Foo로 할지 직접 오버라이딩 해줘야 한다
    }

    @Override
    public String getName () {
        return this.name;
    }
}
