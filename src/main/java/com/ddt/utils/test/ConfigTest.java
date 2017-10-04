package com.ddt.utils.test;

import com.ddt.utils.Config;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * Config Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10/04/2017</pre>
 */
public class ConfigTest extends TestCase {
    public ConfigTest(String name) {
        super(name);
    }

    private Config config = Config.getInstance();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: getInstance()
     */
    public void testGetInstance() throws Exception {
        System.out.printf("config: " + config);
    }

    /**
     * Method: getConfigDirectory()
     */
    public void testGetConfigDirectory() throws Exception {
        System.out.printf("config directory: " + Config.getConfigDirectory());
    }


    /**
     * Method: parseConfigDirectory(String dirPath)
     */
    public void testParseConfigDirectory() throws Exception {
        //TODO: Test goes here...
/* 
try { 
   Method method = Config.getClass().getMethod("parseConfigDirectory", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }


    public static Test suite() {
        return new TestSuite(ConfigTest.class);
    }
} 
