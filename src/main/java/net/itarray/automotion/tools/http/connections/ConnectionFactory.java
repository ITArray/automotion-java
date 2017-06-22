package net.itarray.automotion.tools.http.connections;

import http.connections.*;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.Map;

public class ConnectionFactory extends ConnectionBuilder {

    private String url;

    public ConnectionFactory(String url) {
        super(url);
        this.url = url;
    }

    public CloseableHttpResponse sendPostWithFullResponse(Map map, String endpoint, String token, boolean withMediaFile) throws IOException {
        return new ConnectionBuilder(url).sendPOST(map, endpoint, token, withMediaFile);
    }

    public Map<Integer, String> sendPost(Map map, String endpoint, String token, boolean withMediaFile) throws IOException {
        return new ConnectionBuilder(url).getPOST(map, endpoint, token, withMediaFile);
    }

    public Map<Integer, String> sendPost(Map map, Map headers, String endpoint, String token, boolean withMediaFile) throws IOException {
        return new ConnectionBuilder(url).getPOST(map, headers, endpoint, token, withMediaFile);
    }

    public Map<Integer, String> sendGet(String endpoint, String token) throws IOException {
        return new ConnectionBuilder(url).getGET(endpoint, token);
    }

    public Map<Integer, String> sendGet(String endpoint, Map headers) throws IOException {
        return new ConnectionBuilder(url).getGET(endpoint, headers);
    }

    public Map<Integer, String> sendPut(Map map, String endpoint, String token) throws IOException {
        return new ConnectionBuilder(url).getPUT(map, endpoint, token);
    }

    public Map<Integer, String> sendDelete(String endpoint, String token) throws IOException {
        return new ConnectionBuilder(url).getDELETE(endpoint, token);
    }
}