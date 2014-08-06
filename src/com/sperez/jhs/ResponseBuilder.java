package com.sperez.jhs;

import java.util.ArrayList;

public class ResponseBuilder {
    private String status;
    private ArrayList<String> headers;
    private String body;
    private byte[] binaryBody;
    private Response response;
    private RoutingLogic logic;

    public ResponseBuilder(RoutingLogic logic) {
        this.logic = logic;
    }

    public void setPortAndPublicDir(int port, String publicDir) {
        logic.setPortAndPublicDir(port, publicDir);
    }

    public void setRequestObject(Request request) {
        logic.setRequestObject(request);
    }

    public Response buildResponse() {
        initializeResponseObject();
        response = logic.execute();

        return response;
    }

    private void initializeResponseObject() {
        initResponseFields();
        response = new Response(status, headers, body, binaryBody);
        logic.initializeResponse(response);
    }

    private void initResponseFields() {
        status = "";
        headers = new ArrayList<String>();
        body = "";
        binaryBody = null;
    }
}