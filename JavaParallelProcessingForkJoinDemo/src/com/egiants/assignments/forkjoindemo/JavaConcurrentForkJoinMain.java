package com.egiants.assignments.forkjoindemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
public class JavaConcurrentForkJoinMain {
   public static void main(String[] args) {
      final int SIZE = 10;
      ForkJoinPool pool = new ForkJoinPool();
      double na[] = new double [SIZE];
      System.out.println("initialized random values :");
      for (int i = 0; i < na.length; i++) {
         na[i] = (double) i + Math.random();
         System.out.format("%.4f ", na[i]);
      }
      System.out.println();
      JavaConcurrentForkJoin task = new JavaConcurrentForkJoin(na, 0, na.length);
      pool.invoke(task);
      System.out.println("Changed values :");
      for (int i = 0; i < 10; i++)
      System.out.format("%.4f ", na[i]);
      System.out.println();
   }
}