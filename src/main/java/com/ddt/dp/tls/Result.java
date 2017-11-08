/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.tls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Result {

    private List<Date> dateList = new ArrayList<>();

    private List<String> exceptionList = new ArrayList<>();

    public List<Date> getDateList() {
        return dateList;
    }

    public List<String> getExceptionList() {
        return exceptionList;
    }
}
