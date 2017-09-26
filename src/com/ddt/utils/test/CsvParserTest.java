package com.ddt.utils.test; 

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

/** 
* CsvParser Tester. 
* 
* @author <YuJun> 
* @since <pre>09/22/2017</pre> 
* @version 1.0 
*/ 
public class CsvParserTest extends TestCase { 
public CsvParserTest(String name) { 
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
* Method: getRowCount() 
* 
*/ 
public void testGetRowCount() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getColumnCount(final int row) 
* 
*/ 
public void testGetColumnCount() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getInt(final int row, final int column) 
* 
*/ 
public void testGetInt() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getDouble(final int row, final int column) 
* 
*/ 
public void testGetDouble() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getString(final int row, final int column) 
* 
*/ 
public void testGetString() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: parseCsv() 
* 
*/ 
public void testParseCsv() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CsvParser.getClass().getMethod("parseCsv"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 


public static Test suite() { 
return new TestSuite(CsvParserTest.class); 
} 
} 
