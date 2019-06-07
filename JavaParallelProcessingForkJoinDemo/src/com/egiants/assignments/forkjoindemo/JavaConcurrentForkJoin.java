package com.egiants.assignments.forkjoindemo;

import java.util.concurrent.RecursiveAction;

public class JavaConcurrentForkJoin extends RecursiveAction {
	final int THRESHOLD = 2;
	double[] numbers;
	int indexStart, indexLast;

	JavaConcurrentForkJoin(double[] n, int s, int l) {
		numbers = n;
		indexStart = s;
		indexLast = l;
	}

	@Override
	protected void compute() {
		if ((indexLast - indexStart) > THRESHOLD)
			for (int i = indexStart; i < indexLast; i++)
				numbers[i] = numbers[i] + Math.random();
		else
			invokeAll(new JavaConcurrentForkJoin(numbers, indexStart, (indexStart - indexLast) / 2),
					new JavaConcurrentForkJoin(numbers, (indexStart - indexLast) / 2, indexLast));
	}
}
