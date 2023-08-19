package edu.wgu.capstone.controller.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Global executor pools for the whole application.
 */
public class AppExecutors {
    private static final Object LOCK = new Object();
    private static AppExecutors instance;
    private final Executor diskIO;

    /**
     * Private constructor to prevent instantiation.
     * @param diskIO The disk IO executor.
     */
    private AppExecutors(Executor diskIO) {
        this.diskIO = diskIO;
    }

    /**
     * Returns the singleton instance of the AppExecutors.
     * @return The AppExecutors.
     */
    public static AppExecutors getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new AppExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return instance;
    }

    /**
     * Returns the disk IO executor.
     * @return The disk IO executor.
     */
    public Executor diskIO() {
        return diskIO;
    }
}

