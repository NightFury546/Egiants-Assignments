package com.egiants.assignments.forkjoindemo;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinFibonacciMain {

    public static void main(String[] args) {

        ForkJoinFibonacci task = new ForkJoinFibonacci(50);
        new ForkJoinPool().invoke(task);

        System.out.println(task.getNumber());

    }

}