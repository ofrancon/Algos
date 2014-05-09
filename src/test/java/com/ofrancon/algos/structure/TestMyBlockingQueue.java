package com.ofrancon.algos.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class TestMyBlockingQueue {

	private final static String ENQUEUED = "Enqueued";
	private final static String INTERRUPTED = "Interrupted";
	private final static String SHUTDOWN = "Shutdown";

	private static final int NB_PRODUCERS = 4;
	private static final int NB_CONSUMERS = 4;
	private static final int NB_ELEMENTS = 1000000;
	private static final int QUEUE_SIZE = 10000;

	@Test
	public void testEnqueue() {
		// Create a queue of 3 elements max
		final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(3);
		ExecutorService producers = Executors.newFixedThreadPool(1);
		try {
			// Enqueue 1 element
			ProducerJob job = new ProducerJob(1, queue);
			assertTrue("The queue should be empty", queue.isEmpty());
			Future<String> future = producers.submit(job);
			// Make sure it worked
			String result = future.get();
			assertEquals("Not the expected outcome", ENQUEUED, result);
			assertEquals("Not the expected number of elements in the queue", 1, queue.size());
			assertTrue("The queue should not be empty anymore", !queue.isEmpty());
			assertTrue("The queue should not be full", !queue.isFull());

			// Enqueue a second element.
			job = new ProducerJob(2, queue);
			future = producers.submit(job);
			result = future.get();
			assertEquals("Not the expected outcome", ENQUEUED, result);
			assertEquals("Not the expected number of elements in the queue", 2, queue.size());
			assertTrue("The queue should not be empty anymore", !queue.isEmpty());
			assertTrue("The queue should not be full", !queue.isFull());

			// Enqueue a third element. The queue should be full now.
			job = new ProducerJob(3, queue);
			future = producers.submit(job);
			result = future.get();
			assertEquals("Not the expected outcome", ENQUEUED, result);
			assertEquals("Not the expected number of elements in the queue", 3, queue.size());
			assertTrue("The queue should not be empty anymore", !queue.isEmpty());
			assertTrue("The queue should be full", queue.isFull());

			// System.out.println("Queue: " + queue);
		} catch (InterruptedException e) {
			fail("The current thread should not have been interrupted");
		} catch (ExecutionException e) {
			fail("Execution exception: " + e.getCause());
		}
	}

	@Test
	public void testEnqueueMultiThread() {
		// Create a queue of 3 elements max
		final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(3);
		ExecutorService producers = Executors.newFixedThreadPool(3);
		ProducerJob job1 = new ProducerJob(1, queue);
		ProducerJob job2 = new ProducerJob(2, queue);
		ProducerJob job3 = new ProducerJob(3, queue);
		Collection<ProducerJob> jobs = new HashSet<ProducerJob>();
		jobs.add(job1);
		jobs.add(job2);
		jobs.add(job3);
		try {
			List<Future<String>> future = producers.invokeAll(jobs);
			assertEquals("Not the expected outcome", 3, future.size());
			assertEquals("Not the expected number of elements in the queue", 3, queue.size());
			assertTrue("The queue should not be empty", !queue.isEmpty());
			assertTrue("The queue should be full", queue.isFull());
		} catch (InterruptedException e) {
			fail("Unexpected InterruptedException");
		}
	}

	@Test
	public void testEnqueueTimeout() {
		// Create a queue of 1 element max
		final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(1);
		ExecutorService producers = Executors.newFixedThreadPool(1);
		try {
			// Enqueue 1 element
			ProducerJob job = new ProducerJob(1, queue);
			assertTrue("The queue should be empty", queue.isEmpty());
			Future<String> future = producers.submit(job);
			// Make sure it worked
			String result = future.get();
			assertEquals("Not the expected outcome", ENQUEUED, result);
			assertEquals("Not the expected number of elements in the queue", 1, queue.size());
			assertTrue("The queue should not be empty anymore", !queue.isEmpty());
			assertTrue("The queue should be full", queue.isFull());

			// Enqueue a second element. Should wait for the result
			job = new ProducerJob(2, queue);
			future = producers.submit(job);
			result = null; // reset the result
			Exception timeoutException = null;
			try {
				// Wait a little bit for the result and throw a TimeoutException
				result = future.get(100, TimeUnit.MILLISECONDS);
			} catch (TimeoutException e) {
				timeoutException = e;
			}
			assertTrue("Was expecting a TimeoutException", timeoutException != null);
			assertEquals("Not the expected outcome", null, result);
		} catch (InterruptedException e) {
			fail("The current thread should not have been interrupted");
		} catch (ExecutionException e) {
			fail("Execution exception: " + e.getCause());
		}
	}

	@Test
	public void testEnqueueShutdown() {
		final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(3);
		ExecutorService producers = Executors.newFixedThreadPool(1);
		try {
			// Enqueue 1 element. Should work.
			ProducerJob job = new ProducerJob(1, queue);
			Future<String> future = producers.submit(job);
			String result = future.get();
			assertEquals("Not the expected outcome", ENQUEUED, result);
			// Shutdown
			queue.shutdown();
			// Enqueue a second element. Should get a QueueShutdownException.
			job = new ProducerJob(2, queue);
			future = producers.submit(job);
			result = future.get();
			assertEquals("Not the expected outcome", SHUTDOWN, result);
		} catch (InterruptedException e) {
			fail("The current thread should not have been interrupted");
		} catch (ExecutionException e) {
			fail("Execution exception: " + e.getCause());
		}
	}

	private MyBlockingQueue<String> createFullQueue(int size) {
		final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(3);
		try {
			for (int i = 1; i <= size; i++) {
				queue.enqueue("E_" + i);
			}
		} catch (InterruptedException e) {
			fail("The current thread should not have been interrupted");
		} catch (QueueShutdownException e) {
			fail("Unexpected QueueShutDownException");
		}
		return queue;
	}

	@Test
	public void testDequeue() {
		// Create a queue of 3 elements max
		MyBlockingQueue<String> queue = createFullQueue(3);
		ExecutorService consumers = Executors.newFixedThreadPool(3);

		try {
			// Dequeue 1 element
			ConsumerJob job = new ConsumerJob(queue);
			Future<String> future = consumers.submit(job);
			String result = future.get();
			assertEquals("Not the expected outcome", "E_1", result);
			assertEquals("Not the expected number of elements in the queue", 2, queue.size());
			assertTrue("The queue should not be empty anymore", !queue.isEmpty());
			assertTrue("The queue should not be full anymore", !queue.isFull());

			// Dequeue the 2nd element
			job = new ConsumerJob(queue);
			future = consumers.submit(job);
			result = future.get();
			assertEquals("Not the expected outcome", "E_2", result);
			assertEquals("Not the expected number of elements in the queue", 1, queue.size());
			assertTrue("The queue should not be empty anymore", !queue.isEmpty());
			assertTrue("The queue should not be full anymore", !queue.isFull());

			// Dequeue the last element
			job = new ConsumerJob(queue);
			future = consumers.submit(job);
			result = future.get();
			assertEquals("Not the expected outcome", "E_3", result);
			assertEquals("Not the expected number of elements in the queue", 0, queue.size());
			assertTrue("The queue should now be empty", queue.isEmpty());
			assertTrue("The queue should not be full anymore", !queue.isFull());
		} catch (InterruptedException e) {
			fail("The current thread should not have been interrupted");
		} catch (ExecutionException e) {
			fail("Execution exception: " + e.getCause());
		}
	}

	@Test
	public void testDequeueMultiThread() {
		// Create a queue of 3 elements
		MyBlockingQueue<String> queue = createFullQueue(3);
		ExecutorService consumers = Executors.newFixedThreadPool(3);

		ConsumerJob job1 = new ConsumerJob(queue);
		ConsumerJob job2 = new ConsumerJob(queue);
		ConsumerJob job3 = new ConsumerJob(queue);
		Collection<ConsumerJob> jobs = new HashSet<ConsumerJob>();
		jobs.add(job1);
		jobs.add(job2);
		jobs.add(job3);

		try {
			List<Future<String>> future = consumers.invokeAll(jobs);
			assertEquals("Not the expected outcome", 3, future.size());
			assertEquals("Not the expected number of elements in the queue", 0, queue.size());
			assertTrue("The queue should be empty", queue.isEmpty());
			assertTrue("The queue should not be full anymore", !queue.isFull());
		} catch (InterruptedException e) {
			fail("Unexpected InterruptedException");
		}
	}

	@Test
	public void testDequeueTimeOut() {
		// Create a queue with 1 element
		MyBlockingQueue<String> queue = new MyBlockingQueue<String>(1);
		assertTrue("The queue should be empty", queue.isEmpty());
		ExecutorService consumers = Executors.newFixedThreadPool(3);

		// Dequeue the element
		ConsumerJob job = new ConsumerJob(queue);
		Future<String> future = consumers.submit(job);

		// Dequeue again and wait
		job = new ConsumerJob(queue);
		future = consumers.submit(job);
		String result = null;
		Exception exception = null;
		try {
			// Wait a little bit for the result and throw a TimeoutException
			result = future.get(100, TimeUnit.MILLISECONDS);
		} catch (TimeoutException e) {
			exception = e;
		} catch (InterruptedException e) {
			exception = e;
		} catch (ExecutionException e) {
			exception = e;
		}
		assertEquals("Was expecting a TimeoutException", TimeoutException.class, exception.getClass());
		assertEquals("Not the expected outcome", null, result);
	}

	@Test
	public void testDequeueShutdown() {
		// Create a queue of 3 elements
		MyBlockingQueue<String> queue = createFullQueue(3);
		ExecutorService consumers = Executors.newFixedThreadPool(3);

		try {
			// Dequeue 1 element. Should work.
			ConsumerJob job = new ConsumerJob(queue);
			Future<String> future = consumers.submit(job);
			String result = future.get();
			assertEquals("Not the expected outcome", "E_1", result);
			// Shutdown
			queue.shutdown();
			// Should still be able to dequeue the 2nd element
			job = new ConsumerJob(queue);
			future = consumers.submit(job);
			result = future.get();
			assertEquals("Not the expected outcome", "E_2", result);
			// Should still be able to dequeue the 3rd element
			job = new ConsumerJob(queue);
			future = consumers.submit(job);
			result = future.get();
			assertEquals("Not the expected outcome", "E_3", result);
			// But should not be able to wait for more elements
			job = new ConsumerJob(queue);
			future = consumers.submit(job);
			result = future.get();
			assertEquals("Not the expected outcome", SHUTDOWN, result);
		} catch (InterruptedException e) {
			fail("The current thread should not have been interrupted");
		} catch (ExecutionException e) {
			fail("Execution exception: " + e.getCause());
		}
	}

	@Test
	public void testMyBlockingQueuePerformance() {
		long before = System.nanoTime();
		// Producers put x elements in the queue, and consumers consume these x
		// elements in parallel.
		final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(QUEUE_SIZE);
		ExecutorService producers = Executors.newFixedThreadPool(NB_PRODUCERS);
		ExecutorService consumers = Executors.newFixedThreadPool(NB_CONSUMERS);

		// Producers jobs
		List<Future<String>> pFutures = new ArrayList<Future<String>>();
		for (int i = 1; i <= NB_ELEMENTS; i++) {
			ProducerJob pJob = new ProducerJob(i, queue);
			Future<String> result = producers.submit(pJob);
			pFutures.add(result);
		}
		// Consumers jobs
		List<Future<String>> cFutures = new ArrayList<Future<String>>();
		for (int i = 1; i <= NB_ELEMENTS; i++) {
			ConsumerJob cJob = new ConsumerJob(queue);
			Future<String> result = consumers.submit(cJob);
			cFutures.add(result);
		}

		checkResults(pFutures);
		checkResults(cFutures);
		long after = System.nanoTime();
		double time = (after - before) / 1000000d;
		System.out.println("Finished all threads");

		System.out.println("Total time: " + time + " ms.");
	}

	@Test
	public void testArrayBlockingQueuePerformance() {
		long before = System.nanoTime();
		// Producers put x elements in the queue, and consumers consume these x
		// elements in parallel.
		final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(QUEUE_SIZE);
		ExecutorService producers = Executors.newFixedThreadPool(NB_PRODUCERS);
		ExecutorService consumers = Executors.newFixedThreadPool(NB_CONSUMERS);

		// Producers jobs
		List<Future<String>> pFutures = new ArrayList<Future<String>>();
		for (int i = 1; i <= NB_ELEMENTS; i++) {
			ProducerJob2 pJob = new ProducerJob2(i, queue);
			Future<String> result = producers.submit(pJob);
			pFutures.add(result);
		}
		// Consumers jobs
		List<Future<String>> cFutures = new ArrayList<Future<String>>();
		for (int i = 1; i <= NB_ELEMENTS; i++) {
			ConsumerJob2 cJob = new ConsumerJob2(queue);
			Future<String> result = consumers.submit(cJob);
			cFutures.add(result);
		}

		checkResults(pFutures);
		checkResults(cFutures);
		long after = System.nanoTime();
		double time = (after - before) / 1000000d;
		System.out.println("Finished all threads");

		System.out.println("Total time: " + time + " ms.");
	}

	private void checkResults(List<Future<String>> results) {
		assertEquals("Did not give the expected number of results", NB_ELEMENTS, results.size());
		for (Future<String> future : results) {
			try {
				String result = future.get();
				assertTrue("Should not have been interrupted", !INTERRUPTED.equals(result));
				assertTrue("Should not have been shutdown", !SHUTDOWN.equals(result));
			} catch (InterruptedException e) {
				fail("Unexpected InterruptedException");
			} catch (ExecutionException e) {
				fail("Unexpected ExecutionException");
			}
		}
	}

	private class ProducerJob implements Callable<String> {
		private final int id;
		private final MyBlockingQueue<String> queue;

		private ProducerJob(int id, MyBlockingQueue<String> queue) {
			this.id = id;
			this.queue = queue;
		}

		@Override
		public String call() {
			String result;
			try {
				String element = "E_" + id;
				queue.enqueue(element);
				result = ENQUEUED;
			} catch (InterruptedException e) {
				result = INTERRUPTED;
			} catch (QueueShutdownException qse) {
				result = SHUTDOWN;
			}
			return result;
		}
	}

	private class ConsumerJob implements Callable<String> {
		private final MyBlockingQueue<String> queue;

		private ConsumerJob(MyBlockingQueue<String> queue) {
			this.queue = queue;
		}

		@Override
		public String call() {
			String result;
			try {
				result = queue.dequeue();
			} catch (InterruptedException e) {
				result = INTERRUPTED;
			} catch (QueueShutdownException qse) {
				result = SHUTDOWN;
			}
			return result;
		}
	}

	private class ProducerJob2 implements Callable<String> {
		private final int id;
		private final BlockingQueue<String> queue;

		private ProducerJob2(int id, BlockingQueue<String> queue) {
			this.id = id;
			this.queue = queue;
		}

		@Override
		public String call() {
			String result;
			try {
				String element = "E_" + id;
				queue.put(element);
				result = ENQUEUED;
			} catch (InterruptedException e) {
				result = INTERRUPTED;
			}
			return result;
		}
	}

	private class ConsumerJob2 implements Callable<String> {
		private final BlockingQueue<String> queue;

		private ConsumerJob2(BlockingQueue<String> queue) {
			this.queue = queue;
		}

		@Override
		public String call() {
			String result;
			try {
				result = queue.take();
			} catch (InterruptedException e) {
				result = INTERRUPTED;
			}
			return result;
		}
	}
}
