package com.ofrancon.algos.structure;

import static org.junit.Assert.fail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class TestMyBlockingQueue {

	private static final int NB_PRODUCERS = 2;
	private static final int NB_CONSUMERS = 2;
	private static final int NB_ELEMENTS = 1000000;

	@Test
	public void testQueue() {
		long before = System.nanoTime();
		// Producers put x elements in the queue, and consumers consume these x
		// elements in parallel.
		final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(10);
		final AtomicInteger p = new AtomicInteger(0);
		final AtomicInteger c = new AtomicInteger(0);
		ExecutorService producers = Executors.newFixedThreadPool(NB_PRODUCERS);
		ExecutorService consumers = Executors.newFixedThreadPool(NB_CONSUMERS);

		for (int i = 0; i < NB_ELEMENTS; i++) {
			final int j = i + 1;
			// System.out.println("Generating 1 job");
			Runnable producerJob = new Runnable() {
				@Override
				public void run() {
					try {
						String element = "E_" + j;
						queue.enqueue(element);
						p.getAndIncrement();
						// System.out.println("Produced: " + element);
					} catch (InterruptedException e) {
						fail("Thread has been unexpectedly interrupted");
					} catch (QueueShutdownException qse) {
						fail("The queue has ben shutdown unexepectedly");
					}
				}
			};
			producers.execute(producerJob);

			Runnable consumerJob = new Runnable() {
				@Override
				public void run() {
					try {
						String element = queue.dequeue();
						c.getAndIncrement();
						// System.out.println("Consumed: " + element);
					} catch (InterruptedException e) {
						fail("Thread has been unexpectedly interrupted");
					} catch (QueueShutdownException qse) {
						fail("The queue has ben shutdown unexepectedly");
					}
				}
			};
			consumers.execute(consumerJob);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		producers.shutdown();
		consumers.shutdown();
		// Wait until all threads are finish
		try {
			producers.awaitTermination(10, TimeUnit.SECONDS);
			consumers.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			fail("Unexepected InterruptedException");
		}
		long after = System.nanoTime();
		double time = (after - before) / 1000000d;
		System.out.println("Finished all threads");
		System.out.println("Produced " + p.get() + " jobs");
		System.out.println("Consumed " + c.get() + " jobs");
		System.out.println("Total time: " + time + " ms.");
	}
}
