package com.ddt.utils;

import java.io.*;

public class Serialize implements Serializable {

    private static final long serialVersionUID = -1351834486597493928L;

    private Serialize(){

    }

    /**
     * byte[] to object
     *
     * @param bytes byte[]
     * @return object
     */
    public static Object byte2Object(byte[] bytes) {
        Object obj = null;
        try {
            // byte array to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("serialize error: " + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * object to byte[]
     *
     * @param obj object
     * @return byte[]
     */
    public static byte[] object2Byte(Object obj) {
        byte[] bytes = null;
        try {
            // object to byte array
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("serialize error:" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

}
