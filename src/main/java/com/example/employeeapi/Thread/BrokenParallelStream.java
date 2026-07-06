//package com.example.employeeapi.Thread;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.IntStream;
//
//public class BrokenParallelStream {
//     static void main(String[] args) {
//        // Shared mutable state (NOT thread-safe)
//        List<Integer> unsafeList = new ArrayList<>();
//
//        // Process numbers from 1 to 1000 in parallel
//        IntStream.rangeClosed(1, 10)
//                .parallel()
//                .forEach(unsafeList::add); // DANGER: Multiple threads call .add() simultaneously
//
//        // Expected output: 1000, actual output: random smaller number or an error
//        System.out.println("Items in list: " + unsafeList.size());
//    }
//}
