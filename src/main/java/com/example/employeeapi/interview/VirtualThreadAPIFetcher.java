package com.example.employeeapi.interview;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadAPIFetcher {

    public void demo(Object obj){

        if(obj instanceof String s){
            System.out.println(s.length());
        }

    }

    public void fetchMassiveDataConcurrently(List<String> urls) {
        // 1. Create an ExecutorService that spawns a lightweight virtual thread for every single task
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            // Shared, modern HTTP client capable of handling heavy concurrent requests
            HttpClient httpClient = HttpClient.newHttpClient();

            List<Future<String>> futures = urls.stream()
                    .map(url -> executor.submit(() -> {
                        // This block runs inside its own isolated Virtual Thread
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();

                        // This blocking call automatically triggers JVM yielding (no OS thread is blocked)
                        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                        System.out.println("Fetched successfully on thread: " + Thread.currentThread());
                        return response.body();
                    }))
                    .toList();

            // The 'try-with-resources' block automatically waits here for ALL 10,000 tasks to finish
            // before exiting, acting like a built-in barrier.
            demo(futures);

        } catch (Exception e) {
            System.err.println("Batch execution encountered an error: " + e.getMessage());
        }

    }
}

