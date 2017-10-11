package com.ddt.utils.test;

import com.ddt.utils.*;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * DateTimeHandler Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10/11/2017</pre>
 */
public class DateTimeHandlerTest extends TestCase {
    public DateTimeHandlerTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: parseDate(final String datetimeStr, final DateFormat dateFormat, final int dateType)
     */
    public void testParseDate() throws Exception {
        String dateStr = "20170921";
        Date date1 = DateTimeHandler.parseDate(dateStr, DateFormat.YYYYMMDD, 1);
        System.out.println("parse: " + date1.toString());
    }

    /**
     * Method: parseDateB(final String dateStr, final DateFormat dateFormat, final int dateType)
     */
    public void testParseDateB() throws Exception {
        //YYYYMMDD
        String dateStr = "20171011";
        Date date = DateTimeHandler.parseDateB(dateStr, DateFormat.YYYYMMDD, 1);
        Assert.assertEquals("2017-10-11", date.toString());

        //YYMMDD
        String dateStr1 = "171011";
        Date date1 = DateTimeHandler.parseDateB(dateStr1, DateFormat.YYMMDD, 1);
        Assert.assertEquals("2017-10-11", date1.toString());

        //MMDDYY
        String dateStr2 = "101117";
        Date date2 = DateTimeHandler.parseDateB(dateStr2, DateFormat.MMDDYY, 1);
        Assert.assertEquals("2017-10-11", date2.toString());

        //MMDDYYYY
        String dateStr3 = "10112017";
        Date date3 = DateTimeHandler.parseDateB(dateStr3, DateFormat.MMDDYYYY, 1);
        Assert.assertEquals("2017-10-11", date3.toString());

        //DDMMYY
        String dateStr4 = "111017";
        Date date4 = DateTimeHandler.parseDateB(dateStr4, DateFormat.DDMMYY, 1);
        Assert.assertEquals("2017-10-11", date4.toString());

        //DDMMYYYY
        String dateStr5 = "11102017";
        Date date5 = DateTimeHandler.parseDateB(dateStr5, DateFormat.DDMMYYYY, 1);
        Assert.assertEquals("2017-10-11", date5.toString());
        //TODO DDMONYY, DDMONYYYY

    }

    /**
     * Method: parseTime(final String timeStr, final TimeFormat timeFormat)
     */
    public void testParseTime() throws Exception {
        String timeStr = "16:39:21";
        Time time = DateTimeHandler.parseTime(timeStr, TimeFormat.HHMMSS_COLON);
        System.out.println("parse time: " + time.toString());
    }

    /**
     * Method: parseDateTime(final String datetimeStr, final DateFormat dateFormat, final int dateType, final TimeFormat timeFormat)
     */
    public void testParseDateTime() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getSystemDate()
     */
    public void testGetSystemDate() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getSystemTime()
     */
    public void testGetSystemTime() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getSystemDateTime()
     */
    public void testGetSystemDateTime() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getSystemMilliseconds()
     */
    public void testGetSystemMilliseconds() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getDayofWeek(final Date dateObject)
     */
    public void testGetDayofWeek() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getDayOfWeekChar(final Date dateObject)
     */
    public void testGetDayOfWeekChar() throws Exception {
        //TODO: Test goes here...
    }


    /**
     * Method: checkDate(short year, byte month, byte day)
     */
    public void testCheckDate() throws Exception {
        //TODO: Test goes here...
/* 
try { 
   Method method = DateTimeHandler.getClass().getMethod("checkDate", short.class, byte.class, byte.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: checkTime(byte hour, byte minute, byte second)
     */
    public void testCheckTime() throws Exception {
        //TODO: Test goes here...
/* 
try { 
   Method method = DateTimeHandler.getClass().getMethod("checkTime", byte.class, byte.class, byte.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }


    public static Test suite() {
        return new TestSuite(DateTimeHandlerTest.class);
    }
} 