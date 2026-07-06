package com.example.employeeapi.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UserAggregationService {

   // private final ExecutorService ioExecutor = Executors.newFixedThreadPool(50);
    ExecutorService ioExecutor = Executors.newVirtualThreadPerTaskExecutor();
    public void demoVirtualThreads() {

        for (int i = 1; i <= 5; i++) {
            int id = i;

            CompletableFuture.runAsync(() -> {
                System.out.println("Task " + id +
                        " -> " + Thread.currentThread());
            }, ioExecutor);
        }

        // ensure main waits for all tasks
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public record UserProfile(String id, String name)
    {

    }
    public record OrderHistory(String id, double totalAmount)
    {

    }
    public record RewardPoints(String id, int points)
    {

    }
    public record AggregateUser(UserProfile profile, OrderHistory order, RewardPoints rewards)
    {

    }
    public CompletableFuture<AggregateUser> getAggregateUserData(String userId){

     CompletableFuture<UserProfile> profileFuture = CompletableFuture
            .supplyAsync(() -> fetchProfile(userId), ioExecutor)
            .completeOnTimeout(new UserProfile(userId, "Guest"), 2 , TimeUnit.SECONDS)
            .exceptionally(ex ->new UserProfile(userId, "Fallback User"));
     CompletableFuture<OrderHistory> ordersFuture = CompletableFuture
             .supplyAsync(()-> fetchOrders(userId), ioExecutor)
             .completeOnTimeout(new OrderHistory(userId, 0.0), 2 , TimeUnit.SECONDS)
             .exceptionally(ex -> new OrderHistory(userId, 0.0));
     CompletableFuture<RewardPoints> rewardsFuture = ordersFuture.thenComposeAsync(orders -> {
         if(orders.totalAmount() == 0.0){
             return CompletableFuture.completedFuture(new RewardPoints(userId, 0));
         }
         return CompletableFuture.supplyAsync(()-> fetchRewards(orders), ioExecutor);
     }, ioExecutor)
             .completeOnTimeout(new RewardPoints(userId, 0), 2 , TimeUnit.SECONDS)
             .exceptionally(ex-> new RewardPoints(userId, 0));

     return  CompletableFuture.allOf(profileFuture, ordersFuture, rewardsFuture)
             .thenApply(v-> new AggregateUser(profileFuture.join(),
                     ordersFuture.join(), rewardsFuture.join()));

    }
    // Mock network operations
    private UserProfile fetchProfile(String id) {
        System.out.println("Profile thread: " + Thread.currentThread());
        /* Network call */ return new UserProfile(id, "Alex");
    }
    private OrderHistory fetchOrders(String id) {
        System.out.println("Orders thread: " + Thread.currentThread());
        return new OrderHistory(id, 1500.50);
        /* Network call */
    }
    private RewardPoints fetchRewards(OrderHistory orders) {
        System.out.println("Rewards thread: " + Thread.currentThread());
        /* Network call */ return new RewardPoints(orders.id(), 150); }
    public void shutdown() {
        ioExecutor.close();
    }

}
