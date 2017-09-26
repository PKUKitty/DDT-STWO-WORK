package com.ddt.utils.test;

import com.ddt.utils.FileUtils;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * FileUtils Tester.
 *
 * @author <YuJun>
 * @version 1.0
 * @since <pre>09/26/2017</pre>
 */
public class FileUtilsTest extends TestCase {

    private String fileName = "C:\\Users\\YuJun\\Desktop\\attributetag.20141205110510.csv";

    public FileUtilsTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: getAbsPath(final String path)
     */
    public void testGetAbsPath() throws Exception {
        System.out.println("abs path: " + FileUtils.getAbsPath(fileName));
    }

    /**
     * Method: getAbsPathS(final String path)
     */
    public void testGetAbsPathS() throws Exception {
        System.out.println("abs path: " + FileUtils.getAbsPathS(fileName));
    }

    /**
     * Method: splitPath(final String path)
     */
    public void testSplitPath() throws Exception {
        String[] paths = FileUtils.splitPath(fileName);
        if (paths == null) {
            return;
        }
        for (String s :
                paths) {
            System.out.println("paths: " + s);
        }
        Assert.assertEquals(5, paths.length);
    }


    public static Test suite() {
        return new TestSuite(FileUtilsTest.class);
    }
} 
