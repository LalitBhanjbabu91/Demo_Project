package com.example.employeeapi.Thread;

import java.util.concurrent.TimeUnit;

public class DeadlockPreventionExample {

    public static boolean transfer(Account fromAcc, Account toAcc, double amount) throws InterruptedException {
        if(fromAcc.lock.tryLock(1, TimeUnit.SECONDS))
        {
            try{
                System.out.println(Thread.currentThread().getName() + " locked " + fromAcc.getId());
                Thread.sleep(50);
                if(toAcc.lock.tryLock(1, TimeUnit.SECONDS)){
                    try{
                        System.out.println(Thread.currentThread().getName() + " locked "+ toAcc.getId());
                        fromAcc.withdraw(amount);
                        toAcc.deposit(amount);
                        System.out.println("Success! Transferred $" + amount + " from " + fromAcc.getId() + " to " + toAcc.getId());
                        return true;
                    }
                    finally {
                        toAcc.lock.unlock();
                    }
                }
                else{
                    System.out.println(Thread.currentThread().getName() + " failed to lock " + toAcc.getId() + ". Backing off.");
                }
            }finally {
                fromAcc.lock.unlock();
            }
        }else{
            System.out.println(Thread.currentThread().getName() + " failed to lock " + fromAcc.getId() + ". Will retry.");
        }
        return false;
    }

    static void main() {
        Account account1 = new Account("Acc-1", 5000);
        Account account2 = new Account("Acc-2", 4000);

        //Transfer from acc-1 to acc-2
        Thread threadA = new Thread(() -> {
            try{
                while (!transfer(account1, account2 , 500)){
                    Thread.sleep(100); // wait briefly before retrying
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }, "Thread-A");

        //Thead B: Transfer from acc-2 to acc-1 (Opposite order triggers deadlock)
        Thread threadB = new Thread(() -> {
            try{
                while (!transfer(account2, account1, 300)){
                    Thread.sleep(100); // wait briefly before retrying
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }, "Thread-B");

        threadA.start();
        threadB.start();
    }
}
