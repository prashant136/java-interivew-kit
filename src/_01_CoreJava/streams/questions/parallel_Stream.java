package _01_CoreJava.streams.questions;

import java.util.*;
import java.util.stream.*;

public class parallel_Stream {


    void main() {

        // --------- parallel stream example ---------- (good for large dataset, you will notice performance diff. b/w $04DSA.streams and parallelStream)
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<100000; i++) {
            list.add(i);
        }

        long start = System.currentTimeMillis();
        // 1. use stream to test performance
        list.stream()
                .map(this::heavyWork)
                .collect(Collectors.toList());
        
        // 2. use stream to test performance
//        list.parallelStream()
//                .map(x -> heavyWork(x))
//                .collect(Collectors.toList());
        
        long end = System.currentTimeMillis();
        System.out.println("time diff to find which one is fast: " + (end - start));

        // ---------------- example of ----------------
        List<Integer> arr = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10));

        arr.stream()
                .map(x -> x*2)
                .forEach(x -> System.out.print(x + " "));


        arr.parallelStream()
                .map(x -> x*2)
                .forEachOrdered(System.out::println); // for orderedList
    }

    int heavyWork(int num) {
        for(int i=1; i<10000; i++) {
            num = num * i;
        }
        return num;
    }

}
