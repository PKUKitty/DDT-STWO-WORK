package com.ddt.utils.test;

import com.ddt.utils.NetUtil;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * NetUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10/04/2017</pre>
 */
public class NetUtilTest extends TestCase {
    public NetUtilTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: getPrimaryIp()
     */
    public void testGetPrimaryIp() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getIp4Address()
     */
    public void testGetIp4Address() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getHostName()
     */
    public void testGetHostName() throws Exception {
        System.out.printf("host name: " + NetUtil.getHostName());
    }

    /**
     * Method: getLocalIpBySocket(final Socket socket)
     */
    public void testGetLocalIpBySocket() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRemoteIpBySocket(final Socket socket)
     */
    public void testGetRemoteIpBySocket() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: ip2Int(String ipAddress)
     */
    public void testIp2Int() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: ipToLong(String ipAddress)
     */
    public void testIpToLong() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: getLocalHostLANAddress()
     */
    public void testGetLocalHostLANAddress() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = NetUtil.getClass().getMethod("getLocalHostLANAddress"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }


    public static Test suite() {
        return new TestSuite(NetUtilTest.class);
    }
} 
