package edu.wgu.capstone;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import edu.wgu.capstone.view.RequestCodeCounter;

/**
 * Unit tests for the {@link RequestCodeCounter} class.
 */
public class RequestCodeCounterTest {

    private RequestCodeCounter counter;

    /**
     * Set up the test environment by creating an instance of {@link RequestCodeCounter}.
     */
    @Before
    public void setUp() {
        counter = RequestCodeCounter.getInstance();
    }

    /**
     * Test that the {@link RequestCodeCounter#getRequestCode()} method increments the request code properly.
     */
    @Test
    public void testGetRequestCodeIncrements() {
        int initialCode = counter.getRequestCode();
        int nextCode = counter.getRequestCode();

        assertEquals("Request code should be incremented by 1", initialCode + 1, nextCode);
    }

    /**
     * Test that the {@link RequestCodeCounter#getInstance()} method returns the same instance when called multiple times.
     */
    @Test
    public void testGetInstanceReturnsSameInstance() {
        RequestCodeCounter instance1 = RequestCodeCounter.getInstance();
        RequestCodeCounter instance2 = RequestCodeCounter.getInstance();

        assertEquals("getInstance should return the same instance", instance1, instance2);
    }
}
