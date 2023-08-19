package edu.wgu.capstone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.wgu.capstone.model.Vacation;


/**
 * Tests for the {@link Vacation} class, which represents a vacation.
 */
public class VacationTest {

    /**
     * Tests the constructor of the {@link Vacation} class.
     */
    @Test
    public void testCreateVacation() {
        Vacation testVacation = new Vacation("Test Vacation", "Hotel", "Description", new java.util.Date(), new java.util.Date());
        testVacation.setId(1L);
        assertNotNull(testVacation.getId());
        assertEquals("Test Vacation", testVacation.getTitle());
        assertEquals("Hotel", testVacation.getHotel());
        assertEquals("Description", testVacation.getDescription());
    }
}
