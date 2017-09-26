package com.ddt.utils.test;

import com.ddt.utils.FixedPointNumber;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * FixedPointNumber Tester.
 *
 * @author <YuJun>
 * @version 1.0
 * @since <pre>09/22/2017</pre>
 */
public class FixedPointNumberTest extends TestCase {
    private FixedPointNumber fixedPointNumber = new FixedPointNumber(100);
    private FixedPointNumber fixedPointNumber1 = new FixedPointNumber(20);

    public FixedPointNumberTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: toString()
     */
    public void testToString() throws Exception {
        Assert.assertEquals("100.0", fixedPointNumber.toString());
    }

    /**
     * Method: isZero()
     */
    public void testIsZero() throws Exception {
        Assert.assertEquals(false, fixedPointNumber.isZero());
    }

    /**
     * Method: add(final FixedPointNumber other)
     */
    public void testAdd() throws Exception {
        Assert.assertEquals(120, fixedPointNumber.add(fixedPointNumber1).toInt());
    }

    /**
     * Method: subtract(final FixedPointNumber other)
     */
    public void testSubtract() throws Exception {
        Assert.assertEquals(-80, fixedPointNumber1.subtract(fixedPointNumber).toInt());
    }

    /**
     * Method: multiply(final FixedPointNumber other)
     */
    public void testMultiply() throws Exception {
        Assert.assertEquals(2000, fixedPointNumber.multiply(fixedPointNumber1).toInt());
    }

    /**
     * Method: divide(final FixedPointNumber other)
     */
    public void testDivideOther() throws Exception {
        Assert.assertEquals(5, fixedPointNumber.divide(fixedPointNumber1).toInt());

    }

    /**
     * Method: divide(final int value)
     */
    public void testDivideValue() throws Exception {
        Assert.assertEquals(10, fixedPointNumber.divide(10).toInt());
    }

    /**
     * Method: remainder(final FixedPointNumber other)
     */
    public void testRemainder() throws Exception {
        Assert.assertEquals(0, fixedPointNumber.remainder(fixedPointNumber1).toInt());
    }

    /**
     * Method: less(final FixedPointNumber other)
     */
    public void testLess() throws Exception {
        Assert.assertEquals(true, fixedPointNumber1.less(fixedPointNumber));
    }

    /**
     * Method: greater(final FixedPointNumber other)
     */
    public void testGreater() throws Exception {
        Assert.assertEquals(true, fixedPointNumber.greater(fixedPointNumber1));
    }

    /**
     * Method: equals(final FixedPointNumber other)
     */
    public void testEquals() throws Exception {
        Assert.assertEquals(true, fixedPointNumber.equals(fixedPointNumber));
    }

    /**
     * Method: greaterEqual(final FixedPointNumber other)
     */
    public void testGreaterEqual() throws Exception {
        Assert.assertEquals(true, fixedPointNumber.greaterEqual(fixedPointNumber1));
    }

    /**
     * Method: lessEquals(final FixedPointNumber other)
     */
    public void testLessEquals() throws Exception {
        Assert.assertEquals(true, fixedPointNumber1.lessEquals(fixedPointNumber));
    }

    /**
     * Method: getValue(int digitsAfterPoint)
     */
    public void testGetValue() throws Exception {
        Assert.assertEquals(100, fixedPointNumber.getValue(0));
    }

    /**
     * Method: round()
     */
    public void testRound() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: toInt()
     */
    public void testToInt() throws Exception {
        Assert.assertEquals(100, fixedPointNumber.toInt());
    }

    /**
     * Method: toDouble()
     */
    public void testToDouble() throws Exception {
        Assert.assertEquals(100.0, fixedPointNumber.toDouble());
    }


    /**
     * Method: createFixedPointNumber(final String value)
     */
    public void testCreateFixedPointNumber() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = FixedPointNumber.getClass().getMethod("createFixedPointNumber", final.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }


    public static Test suite() {
        return new TestSuite(FixedPointNumberTest.class);
    }
} 
