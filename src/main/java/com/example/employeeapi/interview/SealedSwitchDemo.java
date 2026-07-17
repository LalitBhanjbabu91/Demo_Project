package com.example.employeeapi.interview;

public class SealedSwitchDemo {

    public String processDocument(SealedClass doc){
        return switch (doc){
            case SealedClass.LegalDoc l-> "Processing a legal doc---->";
            case SealedClass.Invoice i-> "Processing a billing invoice---->";
        };

    }

    static void main() {
        SealedSwitchDemo sealedSwitchDemo = new SealedSwitchDemo();

        SealedClass legal = new SealedClass.LegalDoc();
        SealedClass invoice = new SealedClass.Invoice();

        System.out.println(sealedSwitchDemo.processDocument(legal));
        System.out.println(sealedSwitchDemo.processDocument(invoice));
    }
}
