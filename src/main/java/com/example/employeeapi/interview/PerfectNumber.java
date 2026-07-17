package com.example.employeeapi.interview;

import java.util.Scanner;

public class PerfectNumber {

    static boolean isPerfect(long n)
    {
        if(n == 1) return false;
        long sum = 1;
        for(long i = 2; i * i <= n ; i++){
            if(n % i == 0){
                sum += i;
                if(i != n/i){
                    sum += n/i;
                }
            }
        }
        return sum == n;
    }

    static void main() {
        Scanner scn = new Scanner(System.in);
        int T = scn.nextInt();

        while(T-- > 0){
            long N = scn.nextLong();
            if(isPerfect(N)){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }

    }
}
