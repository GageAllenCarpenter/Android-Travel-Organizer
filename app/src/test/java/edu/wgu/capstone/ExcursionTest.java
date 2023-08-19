package edu.wgu.capstone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.util.Date;

import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;

/**
 * Tests for the {@link Excursion} class, which represents an excursion.
 */
public class ExcursionTest {

    /**
     * Tests the constructor of the {@link Excursion} class.
     */
    @Test
    public void testCreateExcursion() {
        Vacation testVacation = new Vacation("Test Vacation", "Hotel", "Description", new Date(), new Date());
        testVacation.setId(1L);
        Excursion testExcursion = new Excursion("Test Excursion", "Description", new Date(), new Date(), testVacation.getId());
        testExcursion.setId(1L);
        assertNotNull(testExcursion.getId());
        assertEquals("Test Excursion", testExcursion.getTitle());
        assertEquals("Description", testExcursion.getDescription());
        assertEquals((Long) testVacation.getId(), (Long) testExcursion.getVacationId());
    }
}
