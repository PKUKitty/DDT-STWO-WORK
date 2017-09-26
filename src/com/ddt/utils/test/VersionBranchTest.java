package com.ddt.utils.test;

import com.ddt.utils.VersionBranch;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * VersionBranch Tester.
 *
 * @author <YuJun>
 * @version 1.0
 * @since <pre>09/22/2017</pre>
 */
public class VersionBranchTest extends TestCase {
    public VersionBranchTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: getCodeVersion()
     */
    public void testGetCodeVersion() throws Exception {
        Assert.assertEquals(3, VersionBranch.getCodeVersion());
    }

    /**
     * Method: getSubVersion()
     */
    public void testGetSubVersion() throws Exception {
        Assert.assertEquals(5, VersionBranch.getSubVersion());
    }

    /**
     * Method: getPatch()
     */
    public void testGetPatch() throws Exception {
        Assert.assertEquals(2, VersionBranch.getPatch());
    }

    /**
     * Method: getBranch()
     */
    public void testGetBranch() throws Exception {
        Assert.assertEquals("fix_selector", VersionBranch.getBranch());
    }


    /**
     * Method: getVersionFilePath()
     */
    public void testGetVersionFilePath() throws Exception {

        try {
            Method method = VersionBranch.class.getMethod("getVersionFilePath");
            method.setAccessible(true);
            Object invoke = method.invoke(null);
            Assert.assertEquals("C:\\Users\\YuJun\\IdeaProjects\\Utils\\VERSION", invoke);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: getBranchFilePath()
     */
    public void testGetBranchFilePath() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = VersionBranch.getClass().getMethod("getBranchFilePath"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: readFile(String fileName)
     */
    public void testReadFile() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = VersionBranch.getClass().getMethod("readFile", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }


    public static Test suite() {
        return new TestSuite(VersionBranchTest.class);
    }
} 
