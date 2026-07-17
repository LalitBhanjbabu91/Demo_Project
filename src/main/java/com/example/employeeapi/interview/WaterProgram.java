package com.example.employeeapi.interview;

import java.util.Scanner;

public class WaterProgram {

    static int maxWaterCapacity(int n, int[] heights)
    {
        int result = 0;
        int left = 0;
        int right = n - 1;

        while(left < right){
            int area = Math.min(heights[left], heights[right]) * (right - left);
            result = Math.max(result, area);

            if(heights[left] < heights[right]){
                left++;
            }
            else {
                right--;
            }
        }
        return result;

    }

    static void main() {

        Scanner scn = new Scanner(System.in);

        //input number of heights
        int n = scn.nextInt();

        int[] heights = new int[n];
        for(int i = 0; i < n; i++)
        {
            heights[i] = scn.nextInt();
        }
        //Print maximum water capacity
        System.out.println(maxWaterCapacity(n, heights));
        scn.close();
    }
}


