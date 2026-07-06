package com.example.employeeapi.Thread;

import org.apache.tomcat.util.http.parser.TE;

public class DeadlockPreventionExpample2 {

      Account acc1 = new Account("A101", 10000);
      Account acc2 = new Account("A102", 8000);
      Account acc3 = new Account("A103", 7000);


      Thread t1= new Thread(() -> {

          acc1.transfer(acc1, acc2, 1000);
      },"T1");


      Thread t2 = new Thread(() -> {
          acc2.transfer(acc2, acc3, 500);
      }, "T2");

      Thread t3 = new Thread(() -> {

          acc3.transfer(acc3, acc1, 700);
      }, "T3");

      Thread t4 = new Thread(() -> {

          acc2.transfer(acc2, acc1, 400);
      }, "T4");

    static void main() throws InterruptedException {

        DeadlockPreventionExpample2 obj = new DeadlockPreventionExpample2();
        obj.t1.start();
        obj.t2.start();
        obj.t3.start();
        obj.t4.start();

        obj.t1.join();
        obj.t2.join();
        obj.t3.join();
        obj.t4.join();

        System.out.println("Account 101 balance "+ obj.acc1.getBalance());
        System.out.println("Account 102 balance "+ obj.acc2.getBalance());
        System.out.println("Account 103 balance "+ obj.acc3.getBalance());


    }

}
