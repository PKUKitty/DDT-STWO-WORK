package com.ddt.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManifestWriter {

    class Element {
        public String key;

        public String value;

        public Element(final String key, final String value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final String MANIFEST_FILE_NAME = "Manifest";
    private static final String SECTION_GENERAL = "general";
    private static final String SECTION_REQUIRES = "requires";
    private static final String SECTION_INDB = "input_databases";
    private static final String SECTION_INRAW = "input_rawdata";
    private static final int BUFFER_SIZE = 1024 * 1024;

    private String fileName;

    private String dbName;

    private int version;

    private String vintage;

    private String start;

    private String end;

    private String hostIp;

    private int threadCount;

    private boolean update;

    private ArrayList<Element> manifestRequires;

    private ArrayList<Element> manifestInputDbs;

    private ArrayList<Element> manifestInputRawDatas;


    public ManifestWriter() {
        fileName = null;
        dbName = null;
        hostIp = null;
        version = 0;
        threadCount = 0;
        vintage = null;
        update = false;
        start = null;
        end = null;
        manifestRequires = new ArrayList<>();
        manifestInputDbs = new ArrayList<>();
        manifestInputRawDatas = new ArrayList<>();
    }

    public boolean open(final String filePath) {
        if (isOpen()) {
            System.out.println("ERROR: ManifestWriter::open on already open file!");
            return false;
        }
        this.fileName = filePath;
        return true;
    }

    public boolean isOpen() {
        return (fileName != null);
    }

    public boolean update(final String filePath, final String oldFilePath /*NULL*/) {
        //TODO
        return false;
    }

    public boolean close() {
        if (!isOpen()) {
            return false;
        }
        if (!this.write()) {
            this.clean();
            return false;
        }
        return this.clean();
    }

    public boolean setDbName(final String dbName) {
        this.dbName = dbName;
        return true;
    }

    public boolean setDbVersion(final int version) {
        this.version = version;
        return true;
    }

    public boolean setVintage(final String vintage) {
        // YYYYMMDD_HHMMSS
        if (vintage.length() != 15 && vintage.length() != 31) {
            System.out.printf("Invalid vintage size [[[%s]]]\n", vintage);
        }

        for (int i = 0; i < 15; i++) {
            if ((i == 8) && (vintage.charAt(i) != '_')) {
                System.out.printf("Invalid vintage [[[%s]]]\n", vintage);
            } else if ((i != 8) && (Character.isDigit(vintage.charAt(i)))) {
                System.out.printf("Invalid vintage [[[%s]]]\n", vintage);
            }
        }

        if (0 == start.length()) {
            System.out.printf("DB build start time is not set, it must be set before setVintage!");
        }

        this.vintage = "";
        this.vintage = vintage.substring(0, 14);
        this.vintage += "-";
        this.vintage += start;
        return true;
    }

    public boolean setBuildHostIp(final String hostIp) {
        this.hostIp = hostIp;
        return true;
    }

    public boolean setBuildHostIp() {
        return setBuildHostIp(NetUtil.getPrimaryIp());
    }

    public boolean setBuildThreadsCount(final int threadCount) {
        if (this.threadCount < threadCount) {
            this.threadCount = threadCount;
        }
        return true;
    }

    public boolean setBuildType(final boolean update) {
        this.update = update;
        return true;
    }

    public boolean addRequirement(final String dbName, final int dbVersion, final String vintage) {
        Element el = new Element(dbName, String.valueOf(dbVersion) + ":" + vintage);
        manifestRequires.add(el);
        return true;
    }

    public boolean clearRequirements() {
        manifestRequires.clear();
        return true;
    }

    public boolean clearInputDbs() {
        manifestInputDbs.clear();
        return true;
    }

    public boolean addInputRawData(final String filename, final String fileAbsPath) {
        Element el = new Element(filename, fileAbsPath);
        manifestInputRawDatas.add(el);
        return true;
    }

    public boolean clearInputRawData() {
        manifestInputRawDatas.clear();
        return true;
    }

    public boolean startBuild() {
        start = getCurrentDateTime();
        return true;
    }

    public boolean endBuild() {
        end = getCurrentDateTime();
        return true;
    }


    private boolean write() {
        if (null == fileName) {
            // Not open yet or already closed
            return false;
        }
        //TODO

        return true;
    }


    private boolean clean() {
        // clean
        manifestRequires.clear();
        manifestInputDbs.clear();
        manifestInputRawDatas.clear();
        fileName = null;
        return true;
    }

    private boolean addRequirement(final String dbName, final String verAndVintage) {
        Element el = new Element(dbName, verAndVintage);
        manifestRequires.add(el);
        return true;
    }

    private boolean addInputDb(final String dbName, final int dbVersion, final String vintage) {
        Element el = new Element(dbName, String.valueOf(dbVersion) + ":" + vintage);
        manifestInputDbs.add(el);
        return true;
    }

    private String getCurrentDateTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(d);
    }


}
