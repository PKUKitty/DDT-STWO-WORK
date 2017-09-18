package com.ddt.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class Runtime {

    public static int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        int index = name.indexOf("@");
        int pid = 0;
        if (index != -1) {
            pid = Integer.parseInt(name.substring(0, index));
        }
        return pid;
    }
}
