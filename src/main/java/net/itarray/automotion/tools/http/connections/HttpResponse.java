package net.itarray.automotion.tools.http.connections;

public class HttpResponse {

    private int responseCode;
    private String responseBody;

    public HttpResponse() {
    }

    public HttpResponse(int responseCode, String responseBody) {
        this.responseBody = responseBody;
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
