package main.java.com.ddt.utils;

import java.io.File;

public class Config extends IniParser {
    private static Config m_instance = null;

    private Config() {

    }

    public static Config getInstance() {
        if (m_instance == null) {
            synchronized (Config.class) {
                if (m_instance == null) {
                    m_instance = new Config();
                    m_instance.parseConfigDirectory(getConfigDirectory());
                }
            }
        }
        return m_instance;
    }

    public static String getConfigDirectory() {
        String baseConfigDir = "/";
        String home = System.getenv("HOME");
        if (!home.isEmpty()) {
            baseConfigDir = home;
        }
        return baseConfigDir.concat("etc"); //TODO
    }


    private void parseConfigDirectory(String dirPath) {
        File iniFiles = new File(dirPath);
        if (iniFiles.isDirectory() && iniFiles.length() != 0) {
            File[] files = iniFiles.listFiles();
            assert files != null;
            for (File file :
                    files) {
                if (file.getName().endsWith(".ini")) {
                    parse(file.getPath());
                }
            }
        }
    }


    public static void main(String[] args){
        Config config = Config.getInstance();
        System.out.println("HOME: " + System.getenv("HOME"));
    }

}
