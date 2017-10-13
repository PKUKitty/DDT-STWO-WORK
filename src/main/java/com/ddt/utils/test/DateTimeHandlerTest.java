package com.ddt.utils.test;

import com.ddt.utils.*;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.junit.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

        //DDMONYY, DDMONYYYY
        String dateStr6 = "11OCT2017";
        Date date6 = DateTimeHandler.parseDateB(dateStr6, DateFormat.DDMONYYYY, 1);
        Assert.assertEquals("2017-10-11", date6.toString());
        System.out.println("date: " + date6.toString());

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
     * Method: parseTimeB(final String timeStr, final TimeFormat timeFormat)
     */
    public void testParseTimeB() throws Exception {
        String timeStr = "16:39:21";
        Time time = DateTimeHandler.parseTimeB(timeStr, TimeFormat.HHMMSS_COLON);
        System.out.println("parse time B: " + time.toString());

        String timeStr1 = "16:39";
        Time time1 = DateTimeHandler.parseTimeB(timeStr1, TimeFormat.HHMM_COLON);
        System.out.println("parse time B: " + time1.toString());

        String timeStr3 = "163921";
        Time time3 = DateTimeHandler.parseTimeB(timeStr3, TimeFormat.HHMMSS);
        System.out.println("parse time B: " + time3.toString());

        String timeStr4 = "1639";
        Time time4 = DateTimeHandler.parseTimeB(timeStr4, TimeFormat.HHMM);
        System.out.println("parse time B: " + time4.toString());

    }

    /**
     * Method: parseDateTime(final String datetimeStr, final DateFormat dateFormat, final int dateType, final TimeFormat timeFormat)
     */
    public void testParseDateTime() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: parseDateTimeB(final String datetimeStr, final DateFormat dateFormat, final int dateType, final TimeFormat timeFormat)
     */
    public void testParseDateTimeB() throws Exception {
        String datetimeStr = "201710111639";
        DateTime dt = DateTimeHandler.parseDateTimeB(datetimeStr, DateFormat.YYYYMMDD, 1, TimeFormat.HHMM);
        System.out.println("parse date time B: " + dt.toString());
    }


    /**
     * Method: getSystemDate()
     */
    public void testGetSystemDate() throws Exception {
        System.out.println("get system date: " + DateTimeHandler.getSystemDate().toString());
    }

    /**
     * Method: getSystemTime()
     */
    public void testGetSystemTime() throws Exception {
        System.out.println("get system time: " + DateTimeHandler.getSystemTime().toString());
    }

    /**
     * Method: getSystemDateTime()
     */
    public void testGetSystemDateTime() throws Exception {
        System.out.println("get system date time: " + DateTimeHandler.getSystemDateTime().toString());
    }

    /**
     * Method: getSystemMilliseconds()
     */
    public void testGetSystemMilliseconds() throws Exception {
        System.out.println("get system milli seconds: " + DateTimeHandler.getSystemMilliseconds());
    }

    /**
     * Method: getDayofWeek(final Date dateObject)
     */
    public void testGetDayofWeek() throws Exception {
        Date date = new Date(12, 10, 2017);
        Assert.assertEquals(4, DateTimeHandler.getDayofWeek(date));
    }

    /**
     * Method: getDayOfWeekChar(final Date dateObject)
     */
    public void testGetDayOfWeekChar() throws Exception {
        Date date = new Date(12, 10, 2017);
        Assert.assertEquals('4', DateTimeHandler.getDayofWeekChar(date));
    }


//    /**
//     * Method: checkDate(short year, byte month, byte day)
//     */
//    public void testCheckDate() throws Exception {
//        Date date = new Date(12, 14, 2017);
//
//        try {
//            Method method = DateTimeHandler.class.getDeclaredMethod("checkDate", short.class, byte.class, byte.class);
//            method.setAccessible(true);
//            Object returnValue = method.invoke(DateTimeHandler.class, date.getYear(), date.getMonth(), date.getDay());
//            Assert.assertEquals(false, returnValue);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Method: checkTime(byte hour, byte minute, byte second)
//     */
//    public void testCheckTime() throws Exception {
//        Time time = new Time(10, 11, 11);
//
//        try {
//            Method method = DateTimeHandler.class.getDeclaredMethod("checkTime", byte.class, byte.class, byte.class);
//            method.setAccessible(true);
//            Object retVal = method.invoke(DateTimeHandler.class, time.getHour(), time.getMinute(), time.getSecond());
//            Assert.assertEquals(true, retVal);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//    }


    public static Test suite() {
        return new TestSuite(DateTimeHandlerTest.class);
    }
} 
