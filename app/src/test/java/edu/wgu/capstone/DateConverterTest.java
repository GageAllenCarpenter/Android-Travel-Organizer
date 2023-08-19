package edu.wgu.capstone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.util.Date;

import edu.wgu.capstone.controller.convertor.DateConverter;

/**
 * Tests for the {@link DateConverter} class, which is responsible for converting Date objects to
 * long timestamps and vice versa.
 */
public class DateConverterTest {

    /**
     * Tests the {@link DateConverter#fromTimestamp(Long)} method to convert a valid timestamp to a Date object.
     */
    @Test
    public void testFromTimestamp() {
        Long timestamp = 1629178200000L;
        Date date = DateConverter.fromTimestamp(timestamp);
        assertEquals((Long) timestamp, (Long) date.getTime());
    }

    /**
     * Tests the {@link DateConverter#fromTimestamp(Long)} method with a null input value.
     */
    @Test
    public void testFromTimestampWithNullValue() {
        Date date = null;
        assertNull(date);
    }

    /**
     * Tests the {@link DateConverter#dateToTimestamp(Date)} method to convert a Date object to a valid timestamp.
     */
    @Test
    public void testDateToTimestamp() {
        Long timestamp = 1629178200000L;
        Date date = new Date(timestamp);
        Long result = DateConverter.dateToTimestamp(date);
        assertEquals(timestamp, result);
    }

    /**
     * Tests the {@link DateConverter#dateToTimestamp(Date)} method with a null input date.
     */
    @Test
    public void testDateToTimestampWithNullValue() {
        Long result = null;
        assertNull(result);
    }
}
