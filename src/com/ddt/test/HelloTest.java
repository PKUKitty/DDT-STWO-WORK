package com.ddt.test;

import com.ddt.common.AircraftTypeCode;
import com.ddt.location.Location;
import com.ddt.location.RegionLocations;
import com.ddt.locationfactory.LocationFactory;
import com.ddt.utils.Databases;
import com.ddt.utils.DatabasesIndex;
import com.ddt.utils.*;
import com.ddt.zmq.*;

import java.util.Base64;
import java.util.UUID;


public class HelloTest {
    public static void main(String[] args) {
//        testSerialize();
//        testLocation();
//        testAdList();
//        testCsvParser();
        //testSizedString();
        //testRedis();
//        testTimer();
    }



    private static void testLocation() {
        Location location = new Location();
        LocationFactory locationFactory = new LocationFactory();
        String fileName = "C:\\Users\\YuJun\\Desktop\\attributetag.20141205110510.csv";
        locationFactory.parseCCD(fileName);

    }



    private static void testAdList() {
        AdList list = new AdList();
        list.addNodeTail(1);
        list.addNodeTail(2);
        list.addNodeTail(3);

        System.out.println(list.getLength());

        list.addNodeHead(5);
        list.addNodeHead("a");
        list.addNodeHead("b");
        System.out.println("list length: " + list.getLength());

        FunType funType = new FunType() {
            @Override
            public Object objectDup(Object object) {
                return object;
            }

            @Override
            public boolean compare(Object key1, Object key2) {
                return true;
            }
        };

        list.setFunType(funType);
        AdListNode node = list.searchKey(5);
        if (node == null) {
            System.out.println("NOT find");
        } else {
            System.out.println("node value: " + node.value);
        }

    }



}
