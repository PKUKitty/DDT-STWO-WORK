package com.ddt.utils;

import java.util.*;

public class Section {
    private String m_sectionName = null;
    private HashMap<String, String> m_properties = null;

    public String getString(String key){
        String value = m_properties.get(key);
        if(value == null){
            //TODO fatal error;
        }
        return value;
    }

    public int getInt(String key){
        return Integer.valueOf(getString(key));
    }

    public double getDouble(String key){
        return Double.valueOf(getString(key));
    }

    public long getLong(String key){
        return Long.valueOf(getString(key));
    }

    public HashSet getSecondarySet(String key){
        HashSet configSet = new HashSet();
        String value;
        if((value =getString(key)).trim().isEmpty()){
            return configSet;
        }

        String[] subStr = value.split(",");
        for (String aSubStr : subStr) {
            if (aSubStr.isEmpty()) {
                continue;
            }
            configSet.add(aSubStr.trim());
        }

        if(subStr.length != configSet.size()){
            //TODO fatal error;
        }
        return configSet;
    }

    public String getPath(String key){
        return getString(key);
    }

    public boolean getBoolean(String key){
        return (Objects.equals(getString(key), "true"));
    }

    public Set<String> getKeys(){
        return m_properties.keySet();
    }

    public HashMap<String, String> getKeyValueMap(){
        return m_properties;
    }

    public void setKeyValueMap(String name, HashMap<String, String> keyValueMap){
        m_sectionName = name;
        m_properties = keyValueMap;
    }
}
