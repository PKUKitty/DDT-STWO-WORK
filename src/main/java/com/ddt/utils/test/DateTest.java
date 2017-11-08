package com.ddt.utils.test;

import com.ddt.utils.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Date Tester.
 *
 * @author <Yu Jun>
 * @version 1.0
 * @since <pre>Nov 8, 2017</pre>
 */
public class DateTest {

    private Date date1 = new Date(11, 11, 2017);

    private Date date2 = new Date(12, 11, 2017);

    private Date date3 = new Date(12, 11, 2017);


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: createDate(final int day, final int month, final int year)
     */
    @Test
    public void testCreateDate() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: setDate(int day, int month, int year)
     */
    @Test
    public void testSetDateForDayMonthYear() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: setDate(java.util.Date javaUtilDate)
     */
    @Test
    public void testSetDateJavaUtilDate() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getDay()
     */
    @Test
    public void testGetDay() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: setDay(byte day)
     */
    @Test
    public void testSetDay() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getMonth()
     */
    @Test
    public void testGetMonth() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: setMonth(byte month)
     */
    @Test
    public void testSetMonth() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getYear()
     */
    @Test
    public void testGetYear() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setYear(short year)
     */
    @Test
    public void testSetYear() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: checkDate(Date date)
     */
    @Test
    public void testCheckDateDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: checkDate(short year, byte month, byte day)
     */
    @Test
    public void testCheckDateForYearMonthDay() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: plusDays(final int days)
     */
    @Test
    public void testPlusDays() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: minusDays(int days)
     */
    @Test
    public void testMinusDays() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: add(int years, int months, int days)
     */
    @Test
    public void testAdd() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: subtract(int years, int months, int days)
     */
    @Test
    public void testSubtract() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: calcDaysDifference(final Date dateObject)
     */
    @Test
    public void testCalcDaysDifference() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: calcYearsDifference(final Date dateObject)
     */
    @Test
    public void testCalcYearsDifference() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: less(final Date dateObject)
     */
    @Test
    public void testLess() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: greater(final Date dateObject)
     */
    @Test
    public void testGreater() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: equals(final Date dateObject)
     */
    @Test
    public void testEquals() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: compareTo(Date other)
     */
    @Test
    public void testCompareTo() throws Exception {
        Assert.assertEquals(-1, date1.compareTo(date2));
        Assert.assertEquals(true, date1.compareTo(date2) < 0);
        Assert.assertEquals(true, date1.compareTo(date2) < 0);
        Assert.assertEquals(true, date2.compareTo(date3) == 0);
    }


} 
