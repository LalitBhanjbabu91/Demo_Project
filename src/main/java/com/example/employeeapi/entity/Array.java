//package com.example.employeeapi.entity;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Array {
//
//        public static int lengthOfLongestSubstring(String s) {
//            // 1. Create array and fill it with -1 (meaning "never seen")
//            int[] lastSeen = new int[128];
//            Arrays.fill(lastSeen, -1);
//
//            int maxLength = 0;
//            int left = 0;
//
//            for (int right = 0; right < s.length(); right++) {
//                char currentChar = s.charAt(right);
//
//                // 2. If we saw this char inside our current window, jump left past it
//                if (lastSeen[currentChar] >= left) {
//                    left = lastSeen[currentChar] + 1;
//                }
//
//                // 3. Store the exact current index
//                lastSeen[currentChar] = right;
//
//                // 4. Calculate size cleanly
//                maxLength = Math.max(maxLength, right - left + 1);
//            }
//
//            return maxLength;
//        }
//
//
//    static void main(String[] args) {
//                System.out.println(lengthOfLongestSubstring("kratika"));
//
//        List<String> items = new ArrayList<>();
//        items.add("Apple");
//        items.add("Banana");
//        items.add("Cherry");
//        items.forEach(System.out::println);
//
//            }
//        }
//
//
//
//
