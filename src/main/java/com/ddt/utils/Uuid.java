package com.ddt.utils;


import com.ddt.common.UUID;

import java.net.Socket;
import java.util.Base64;

public class Uuid {

    static class UuidStore {

        public static final String m_connector = "@";
        // Timestamp of the connection establishment
        long m_timeStamp;

        // Client / Server IPs and Ports
        int m_serverIp = 0;
        int m_clientIp = 0;
        short m_serverPort = 0; // MAX VALUE: 32767, general 80;
        short m_clientPort = 0;

        // Service Process Pid
        int m_pid;

        // Service Code VersionBranch
        char m_codeVersion;
        char m_subversion;
        short m_patch;

        // Request Type
        char m_requestType;

        public String toString() {
            return String.valueOf(m_timeStamp) + m_connector + String.valueOf(m_serverIp) + m_connector + String.valueOf(m_clientIp)
                    + m_connector + String.valueOf(m_serverPort) + m_connector + String.valueOf(m_serverPort) + m_connector + String.valueOf(m_pid)
                    + m_connector + String.valueOf(m_codeVersion) + m_connector + String.valueOf(m_subversion) + m_connector + String.valueOf(m_patch)
                    + m_connector + String.valueOf(m_requestType);
        }

    }

    private UuidStore m_store = new UuidStore();
    private UUID m_encodedUuid;

    public Uuid() {
        m_store.m_timeStamp = System.currentTimeMillis();

        m_store.m_pid = Runtime.getPid();
        m_store.m_codeVersion = 0;
        m_store.m_subversion = 0;
        m_store.m_patch = 0;

        m_store.m_requestType = 0;

        init();
    }

    public Uuid(final Uuid uuid) {
        copy(uuid);
        m_encodedUuid = uuid.m_encodedUuid;
    }

    public Uuid(final Socket socket) {
        m_store.m_timeStamp = System.currentTimeMillis();

        if (socket != null) {
            m_store.m_serverIp = 0; //TODO
            m_store.m_clientIp = 0;
            m_store.m_serverPort = 0;
            m_store.m_clientPort = 0;
        }

        m_store.m_pid = Runtime.getPid();
        m_store.m_codeVersion = 0;
        m_store.m_subversion = 0;
        m_store.m_patch = 0;

        m_store.m_requestType = 0;

        init();
    }

    public Uuid(final UUID uuid) {

        byte[] decodedStr = Base64.getUrlDecoder().decode(String.valueOf(uuid.getData()));

        String tmp = new String(decodedStr);
        String[] datas = tmp.split(UuidStore.m_connector);
        m_store.m_timeStamp = Long.valueOf(datas[0]);

        m_store.m_serverIp = Integer.valueOf(datas[1]);
        m_store.m_clientIp = Integer.valueOf(datas[2]);
        m_store.m_serverPort = Short.valueOf(datas[3]);
        m_store.m_clientPort = Short.valueOf(datas[4]);

        m_store.m_pid = Integer.valueOf(datas[5]);

        m_store.m_codeVersion = datas[6].charAt(0);
        m_store.m_subversion = datas[7].charAt(0);
        m_store.m_patch = Short.valueOf(datas[8]);
        m_store.m_requestType = datas[9].charAt(0);

        m_encodedUuid = uuid;
    }

    public UUID toEncodedSizedString() {
        return m_encodedUuid;
    }

    public String getEncodeString() {
        return m_encodedUuid.trimString();
    }

    public boolean equal(final Uuid uuid) {
        return m_store == uuid.m_store;//TODO equal
    }

    private void copy(final Uuid uuid) {
        m_store = uuid.m_store;
    }

    private void init() {
        String encodedStr = Base64.getUrlEncoder().encodeToString(m_store.toString().getBytes());
        assert (encodedStr.length() == UUID.UUID_LEN);
        m_encodedUuid = new UUID(encodedStr);
    }
}
