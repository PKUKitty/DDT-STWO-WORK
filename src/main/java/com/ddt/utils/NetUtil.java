package com.ddt.utils;

import java.net.*;
import java.util.Enumeration;

public class NetUtil {

    private NetUtil(){

    }


    /**
     * get primary ip address. eg.172.27.48.15
     *
     * @return ip address
     */
    public static String getPrimaryIp() {
        try {
            return getLocalHostLANAddress().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getIp4Address() {
        return 0;
    }

    /**
     *  TODO fix it to return host name as 'yujun-E7440'
     *
     * @return host name
     */
    public static String getHostName(){
        try {
            return getLocalHostLANAddress().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * http://www.cnblogs.com/starcrm/p/7071227.html
     *
     * @return InetAddress
     * @throws UnknownHostException unknown hostname;
     */
    private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

    public static String getLocalIpBySocket(final Socket socket) {
        if (socket != null) {
            return socket.getLocalAddress().toString();
        }
        return null;
    }

    public static String getRemoteIpBySocket(final Socket socket) {
        if (socket != null) {
            return socket.getRemoteSocketAddress().toString();
        }
        return null;
    }

    // example : 192.168.1.2
    public static int ip2Int(String ipAddress) {

        // ipAddressInArray[0] = 192
        String[] ipAddressInArray = ipAddress.split("\\.");

        int result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {

            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);

            // 1. 192 * 2^3
            // 2. 168 * 2^2
            // 3. 1 * 2^1
            // 4. 2 * 2^0
            result += ip * Math.pow(2, power);
        }
        return result;
    }

    public static long ipToLong(String ipAddress) {

        long result = 0;

        String[] ipAddressInArray = ipAddress.split("\\.");

        for (int i = 3; i >= 0; i--) {

            long ip = Long.parseLong(ipAddressInArray[3 - i]);

            // left shifting 24,16,8,0 and bitwise OR

            // 1. 192 << 24
            // 1. 168 << 16
            // 1. 1 << 8
            // 1. 2 << 0
            result |= ip << (i * 8);

        }

        return result;
    }

}
