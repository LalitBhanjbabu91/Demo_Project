package com.example.employeeapi.Thread;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private final String id;
    private double balance;
    final ReentrantLock lock = new ReentrantLock();

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void transfer(Account from, Account to, double amount)
    {
        Account first;
        Account second;

        if(from.getId().compareTo(to.getId()) < 0){
            first = from;
            second = to;
        }else {
            first = to;
            second = from;
        }
        synchronized (first){
            System.out.println(Thread.currentThread().getName()+ " locked " + first.getId());
            synchronized (second){
                System.out.println(Thread.currentThread().getName()+ " locked " + second.getId());
                from.withdraw(amount);
                to.deposit(amount);

                System.out.println(Thread.currentThread().getName()+ " transferred " + amount + " from "
                + from.getId() + " to " +to.getId());
            }
        }

    }
}
