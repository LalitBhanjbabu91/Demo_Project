package com.example.employeeapi.interview;

public interface Demo {

    default void Deposit(double balance) {
    }

    public void withDraw();
    public void balance();

    static boolean isValidAccountId() {
        return false;
    }
}
