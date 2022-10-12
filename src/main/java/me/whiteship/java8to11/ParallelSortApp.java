package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelSortApp {

    public static void main ( String[] args ) {

        int size = 1500;
        int[] numbers = new int[ size ];
        Random random = new Random();

        /* 일반 배열의 정렬 소요시간 */
        // numbers 배열에 랜덤한 값으로 초기화
        IntStream.range( 0, size ).forEach( i -> numbers[ i ] = random.nextInt() );
        long start = System.nanoTime();
        // QuickSort를 사용하여  O(n log(n))의 복잡도( 최악의경우 O(n^2) )를 가지지만 싱글쓰레드라 시간이 좀 걸린다
        Arrays.sort( numbers );
        System.out.println( "serial sorting took " + ( System.nanoTime() - start ) );

        /* parallelSort 정렬 소요시간 */
        IntStream.range( 0, size ).forEach( i -> numbers[ i ] = random.nextInt() );
        start = System.nanoTime();
        Arrays.parallelSort( numbers );
        System.out.println( "parallel sorting took " + ( System.nanoTime() - start ) );
    }
}
