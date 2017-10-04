package com.ddt.utils.test; 

import com.ddt.utils.TimeFormat;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

/** 
* TimeFormat Tester. 
* 
* @author <YuJun> 
* @since <pre>09/22/2017</pre> 
* @version 1.0 
*/ 
public class TimeFormatTest extends TestCase { 
public TimeFormatTest(String name) { 
super(name); 
} 

public void setUp() throws Exception { 
super.setUp(); 
} 

public void tearDown() throws Exception { 
super.tearDown(); 
} 

/** 
* 
* Method: getSeq() 
* 
*/ 
public void testGetSeq() throws Exception {
    Assert.assertEquals(0, TimeFormat.HHMMSS.ordinal());
} 



public static Test suite() { 
return new TestSuite(TimeFormatTest.class); 
} 
} 
