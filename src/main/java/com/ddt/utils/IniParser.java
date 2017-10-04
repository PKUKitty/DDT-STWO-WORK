package main.java.com.ddt.utils;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IniParser {
    private HashMap<String, Section> m_sectionMap = null;

    private static String getEnv(String var) {
        return System.getenv(var);
    }

    /**
     * TODO consider win, linux?
     *
     * @param configFile config file name
     */
    public void parse(String configFile) {
        File file = new File(configFile);
        if (!file.exists() || !file.isFile()) {
            return;
        }
        if (file.length() == 0) {
            return;
        }

        BufferedReader reader = null;
        int lineIdx;
        String line;
        String sectionName = null;
        HashMap<String, String> sectionElements = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                if (line.isEmpty()) {
                    continue;
                }
                lineIdx = 0;
                //skip space and tab
                while (line.charAt(lineIdx) == ' ' || line.charAt(lineIdx) == '\t') {
                    lineIdx++;
                }

                //skip empty line and # note
                if (line.charAt(lineIdx) == '\0' || line.charAt(lineIdx) == '#') {
                    continue;
                }

                //section name
                if (line.charAt(lineIdx) == '[') {
                    if (line.endsWith("]")) {
                        Section section = new Section();
                        if (!Objects.equals(sectionName, "")) {
                            section.setKeyValueMap(sectionName, sectionElements);
                            m_sectionMap.put(sectionName, section);
                        }

                        sectionName = line.substring(1, line.length() - 1);
                        sectionElements.clear();
                    }
                }
                //TODO
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean hasSection(String section) {
            return m_sectionMap.containsKey(section);
    }

    public Section getSection(String section) {
        if(hasSection(section)){
            return m_sectionMap.get(section);
        }
        return null;
    }

    public String getString(String section, String key) {
        return getSection(section).getString(key);
    }

    public int getInt(String section, String key) {
        return getSection(section).getInt(key);
    }

    public double getDouble(String section, String key) {
        return getSection(section).getDouble(key);
    }

    public long getLong(String section, String key) {
        return getSection(section).getLong(key);
    }

    public String getPath(String section, String key) {
        return getSection(section).getPath(key);
    }

    public Set<String> getKeys(String section) {
        return getSection(section).getKeys();
    }

    public String getConfigInfo() {
        return null;
    }

    public boolean hasKey(String section, String key) {
        if(!hasSection(section)){
            return false;
        }

        Section section1 = getSection(section);
        HashMap secMap = section1.getKeyValueMap();
        return secMap.containsKey(key);
    }

    public boolean containSecondaryKey(String section, String key, String secondayKey) {
        return getSection(section).getSecondarySet(key).contains(secondayKey);
    }

    public HashSet<String> getSecondarySet(String section, String key) {
        return getSection(section).getSecondarySet(key);
    }

}
