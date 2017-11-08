/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.tls;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;

public class DateFormatCallable implements Callable<Result> {

    private ThreadLocal<java.text.DateFormat> df;

    private String dateValue;

    public DateFormatCallable(String inDateFormat, String inDateValue) {
        final String idf = inDateFormat;
        this.df = ThreadLocal.withInitial(() -> new SimpleDateFormat(idf));
        this.dateValue = inDateValue;
    }


    @Override
    public Result call() throws Exception {
        System.out.println(Thread.currentThread() + " started executing...");
        Result result = new Result();

        for (int i = 1; i <= 5; i++) {
            try {
                result.getDateList().add(this.df.get().parse(this.dateValue));
            } catch (Exception e) {
                result.getExceptionList().add(e.getClass() + ":" + e.getMessage());
            }
        }

        System.out.println(Thread.currentThread() + "finished processing part of the thread");
        return result;
    }
}
