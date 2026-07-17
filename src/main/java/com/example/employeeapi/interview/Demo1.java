package com.example.employeeapi.interview;


import java.util.*;

public class Demo1{


     void main() {
        Day today = Day.FRIDAY;
        SequencedCollection<String> list = new ArrayList<>();
        list.addLast("Silver");
        list.addFirst("Gold");
        list.addLast("Bronze");
        System.out.println("First element :"+ list.getFirst());
        System.out.println("Last element :" + list.getLast());

        for(String finalList : list.reversed()){
            System.out.println("The items are : "+finalList);
        }

        list.removeFirst();
        list.removeLast();
        System.out.println("After removing elements : " + list);

        SequencedSet<Integer> integers = new LinkedHashSet<>();

        integers.add(10);
        integers.add(20);
        integers.add(30);
        System.out.println("Before changing position" + integers);
        integers.addFirst(30);
        System.out.println("After changing position" + integers);

        SequencedMap<String, String> map = new LinkedHashMap<>();

        map.put("Second", "Two");
        map.putLast("Third", "Three");
        map.putFirst("First", "one");

        Map.Entry<String, String> firstEntry = map.firstEntry();
        System.out.println("First entry :"+ firstEntry);
        Map.Entry<String, String> lastEntry = map.lastEntry();
        System.out.println("Last entry :"+ lastEntry);


        System.out.println("After reversed map :"+ map.reversed());

        IO.println("This 25 version");
        switch (today){
            case SATURDAY , SUNDAY -> System.out.println("Its the weekend");
            default -> System.out.println("Time to work");
        }
    }
}
