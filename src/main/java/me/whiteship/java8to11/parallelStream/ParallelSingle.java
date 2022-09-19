package me.whiteship.java8to11.parallelStream;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class ParallelSingle {
    public static final int TEST_SIZE = 100000;

    public static void main ( String[] args ) {

        ArrayList<Person> people = new ArrayList<>();

        Random random = new Random();

        for ( int i = 0; i < TEST_SIZE; i++ ) {
            people.add( new Person( UUID.randomUUID().toString(), random.nextInt(100), random.nextInt( 100 ) + 100 ) );
        }

        // warming up
        for ( int i = 0; i < 10; i++ ) {
            people.stream().filter( p -> p.getAge() % 2 == 0 );
            people.parallelStream().filter( p -> p.getAge() % 2 == 0 );
        }

        /* simple task */
        System.out.println("Even Age");
        filterTest( people );

        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

        /* Grouping Task */
        // warming up
        for ( int i = 0; i < 10; i++ ) {
            Map<Integer, List<Person>> map1 = people.stream().collect( groupingBy( Person::getAge ) );
            Map<Integer, List<Person>> map2 = people.parallelStream().collect( groupingBy( Person::getAge ) );
        }

        System.out.println("groupingBy");
        groupingByTest( people );

    }

    private static void groupingByTest ( ArrayList<Person> people ) {
        long start = 0, end = 0, runningTime = 0;

        // Single Stream
        start = System.nanoTime();
        Map<Integer, List<Person>> map1 = people.stream().collect( groupingBy( Person::getAge ) );
        end = System.nanoTime();
        runningTime = end - start;
        System.out.println("Single Stream : " + runningTime );


        // Parallel Stream
        start = System.nanoTime();
        Map<Integer, List<Person>> map2 = people.parallelStream().collect( groupingBy( Person::getAge ) );
        end = System.nanoTime();
        runningTime = end - start;
        System.out.println("Single Stream : " + runningTime );
    }

    private static void filterTest ( ArrayList<Person> people ) {

        long start = 0, end = 0, runnungTime = 0;

        // Single Stream
        start = System.nanoTime();
        people.stream().filter( p -> p.getAge() % 2 == 0 );
        end = System.nanoTime();
        runnungTime = end - start;
        System.out.println("Single Stream : " + runnungTime );


        // Parallel Stream
        start = System.nanoTime();
        people.parallelStream().filter( p -> p.getAge() % 2 == 0 );
        end = System.nanoTime();
        runnungTime = end - start;
        System.out.println("Parallel Stream : " + runnungTime );

    }
}
