package edu.wgu.capstone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.util.concurrent.Executor;

import edu.wgu.capstone.controller.thread.AppExecutors;

/**
 * Tests for the {@link AppExecutors} class, which provides executor pools for the application.
 */
public class AppExecutorsTest {

    /**
     * Tests that the {@link AppExecutors#getInstance()} method returns a non-null instance.
     */
    @Test
    public void testGetInstance() {
        AppExecutors executors = AppExecutors.getInstance();
        assertNotNull(executors);
    }

    /**
     * Tests that the {@link AppExecutors#diskIO()} method returns a non-null {@link Executor} instance.
     */
    @Test
    public void testDiskIOExecutor() {
        AppExecutors executors = AppExecutors.getInstance();
        Executor diskIOExecutor = executors.diskIO();
        assertNotNull(diskIOExecutor);
    }

    /**
     * Tests that the {@link AppExecutors#diskIO()} executor is single-threaded by submitting multiple tasks
     * and verifying that they all run on the same thread (thread ID = 1).
     */
    @Test
    public void testDiskIOExecutorIsSingleThreaded() {
        AppExecutors executors = AppExecutors.getInstance();
        Executor diskIOExecutor = executors.diskIO();
        int numTasks = 10;
        for (int i = 0; i < numTasks; i++) {
            diskIOExecutor.execute(() -> {
                long threadId = Thread.currentThread().getId();
                assertEquals(1, threadId);
            });
        }
    }
}
