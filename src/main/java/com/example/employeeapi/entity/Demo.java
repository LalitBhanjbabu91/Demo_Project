//package com.example.employeeapi.entity;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//class Test{
//    private static Test test;
//
//    public static Test getTest()
//    {
//        if(test == null)
//        {
//            test = new Test();
//        }
//        return test;
//    }
//
//    private Test()
//    {
//        if(test != null)
//        {
//            throw new RuntimeException("You are trying to break single ton!!!!");
//        }
//    }
//
///*
//    public static Test getTest()
//    {
//        return test;
//    }
//*/
//
//}
//
//
//public class Demo {
///*
//    //Fixed Thread pool
//    ExecutorService ex1 = Executors.newFixedThreadPool(4);
//
//    //Single thread pool
//    ExecutorService ex2 = Executors.newSingleThreadExecutor();
//
//    //Cached thread pool
//    ExecutorService ex3 = Executors.newCachedThreadPool();
//
//    //Scheduled thread pool
//    ExecutorService ex4 = Executors.newScheduledThreadPool(5);*/
//
//    //public List<T> list;
//
//
//    static void main() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//
//        //Test test1 = Test.getTest();
//
//        //System.out.println(Test.getTest().hashCode());
//
//        //Test test2 = Test.getTest();
//
//
//
//        System.out.println(Test.getTest().hashCode());
//        Constructor<Test> cons = Test.class.getDeclaredConstructor();
//        cons.setAccessible(true);
//        Test test3 = cons.newInstance();
//        System.out.println(test3.hashCode());
//
//
//
//    }
//
//
//}
