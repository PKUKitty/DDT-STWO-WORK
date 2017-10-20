/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.chain;

public class OrcOfficer extends RequestHandler {
    public OrcOfficer(RequestHandler request) {
        super(request);
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType().equals(RequestType.TORTURE_PRISONER)) {
            printHandling(request);
            request.markHandled();
        }
        super.handleRequest(request);
    }

    @Override
    public String toString() {
        return "Orc Officer";
    }
}
