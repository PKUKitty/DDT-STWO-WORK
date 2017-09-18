package com.ddt.utils.test;

import com.ddt.common.AircraftTypeCode;
import com.ddt.location.RegionLocations;
import com.ddt.utils.*;

import java.util.Arrays;

public class UtilsTest {

    public static void main(String[] args) {
        testCsvParser();
        testNetUtil();
        testSerialize();
        testSizedString();
        testTimer();
        testUuid();
    }

    private static void testCsvParser() {
        String fileName = "C:\\Users\\YuJun\\Desktop\\attributetag.20141205110510.csv";
        CsvParser csvParser = new CsvParser(fileName, true);
        System.out.println("csvParser row count: " + csvParser.getRowCount());
        System.out.println("csvParser string: " + csvParser.getString(20, 1));
    }

    private static void testNetUtil() {
        System.out.println(NetUtil.getPrimaryIp());
        System.out.println("ip2Int: " + NetUtil.ip2Int(NetUtil.getPrimaryIp()));
    }

    private static void testSerialize() {
        System.out.println("test enum: " + RegionLocations.REGION_LOCATION_COUNT.ordinal());
        AdList adList = new AdList();
        adList.addNodeTail("1");
        adList.addNodeTail("2");
        adList.addNodeTail("3");
        System.out.println("test serialize: " + adList.getLength());

        byte[] bytes = Serialize.object2Byte(adList);
        System.out.println("test serialize: " + Arrays.toString(bytes));

        Object tmp = Serialize.byte2Object(bytes);
        if (tmp instanceof AdList) {
            AdList list = (AdList) tmp;
            System.out.println("test serialize: " + list.getLength());
            System.out.println("test serialize: " + list.getHead().value);
        }
    }

    private static void testSizedString() {
        AircraftTypeCode aircraftTypeCode = new AircraftTypeCode("B");
        System.out.println("trim: " + aircraftTypeCode.trimString());

        AircraftTypeCode other = new AircraftTypeCode("AAA");
        System.out.println("compare:" + aircraftTypeCode.greater(other));


        SizedString str = aircraftTypeCode.append(other);
        System.out.println("size:" + str.getSize());
        System.out.println("data:" + new String(str.getData(), 0, str.getSize()));
    }

    private static void testUuid() {
        Uuid uuid = new Uuid();
        System.out.println("uuid: " + uuid.toEncodedSizedString().trimString());
        System.out.println("uuid: " + System.currentTimeMillis());

        Uuid uuid1 = new Uuid(uuid.toEncodedSizedString());
        System.out.println("decode: " + uuid1.toEncodedSizedString());
    }

    private static void testTimer() {
        Timer timer = new Timer();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.stop();
        System.out.println("Timer: " + timer.getSeconds());
    }

}
