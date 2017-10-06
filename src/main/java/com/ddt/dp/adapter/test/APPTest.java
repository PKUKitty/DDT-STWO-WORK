package com.ddt.dp.adapter.test;

import com.ddt.dp.adapter.APP;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * APP Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10/05/2017</pre>
 */
public class APPTest extends TestCase {
    public APPTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: main(String[] args)
     */
    public void testMain() throws Exception {
        APP.main(null);
    }


    public static Test suite() {
        return new TestSuite(APPTest.class);
    }
} 
