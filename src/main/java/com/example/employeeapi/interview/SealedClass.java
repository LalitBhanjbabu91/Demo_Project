package com.example.employeeapi.interview;

public abstract sealed class SealedClass permits SealedClass.LegalDoc, SealedClass.Invoice {

    public static  final class LegalDoc extends SealedClass{}
    public static final class Invoice extends  SealedClass{}

}
