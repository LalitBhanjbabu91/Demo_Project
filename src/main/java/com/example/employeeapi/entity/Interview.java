//package com.example.employeeapi.entity;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class Interview {
//
//     static void main(String[] args) {
//        System.out.println("Start small. Ship something.");
//
//        List<List<String>> nestedNames = List.of(
//                List.of("alex", "bob", "charlie"),
//                List.of("BOB", "dan", "alex"),
//                List.of("evan", "charlie", "alex")
//        );
//
//        Map<String, Long> nameCount =
//                nestedNames.stream()
//                        .flatMap(List::stream)
//                        .map(String::toUpperCase)
//                        .collect(Collectors.groupingBy(
//                                name -> name,
//                                Collectors.counting()
//                        ));
//
//        System.out.println("Before final result "+nameCount);
//
//        nameCount.forEach(
//                (name, count) -> System.out.println(name + " " + count)
//        );
//    }
//}
