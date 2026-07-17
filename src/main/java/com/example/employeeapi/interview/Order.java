package com.example.employeeapi.interview;

public class Order {

    private final String orderId;
    private final double amount;
    private final String currency;

    public Order(String orderId, double amount, String currency) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
    }

    public Order(String rawPayload){

        if(rawPayload == null || rawPayload.isBlank()){
            throw new IllegalArgumentException("Payload can not be empty");
        }

        String[] parts = rawPayload.split(",");
        String id = parts[0].trim();

        double basePrice = Double.parseDouble(parts[1].trim());
        double taxRate = Double.parseDouble(parts[2].trim());
        double calculatedTotal = basePrice * (1 + taxRate);

        String currencyCode = parts[3].trim().toUpperCase();

        this(id, calculatedTotal, currencyCode);
    }
}
