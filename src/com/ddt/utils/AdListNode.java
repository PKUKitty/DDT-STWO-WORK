package com.ddt.utils;

import java.io.Serializable;

public class AdListNode implements Serializable{

    private static final long serialVersionUID = -7332962659338205045L;

    public AdListNode prev;

    public AdListNode next;

    public Object value;
}
