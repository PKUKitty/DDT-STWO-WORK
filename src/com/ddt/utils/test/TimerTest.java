package com.ddt.utils.test;

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

/** 
* Timer Tester. 
* 
* @author <Authors name> 
* @since <pre>09/22/2017</pre> 
* @version 1.0 
*/ 
public class TimerTest extends TestCase { 
public TimerTest(String name) { 
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
* Method: start() 
* 
*/ 
public void testStart() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: stop() 
* 
*/ 
public void testStop() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNseconds() 
* 
*/ 
public void testGetNseconds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getSeconds() 
* 
*/ 
public void testGetSeconds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getSecondsAndContinue() 
* 
*/ 
public void testGetSecondsAndContinue() throws Exception { 
//TODO: Test goes here... 
} 



public static Test suite() { 
return new TestSuite(TimerTest.class); 
} 
} 
