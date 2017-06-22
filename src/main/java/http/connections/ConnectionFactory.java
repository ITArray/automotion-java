package http.connections;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * @deprecated As of release 2.0, replaced by{@link net.itarray.automotion.tools.http.connections.ConnectionFactory}
 */
@Deprecated
public class ConnectionFactory {

    private final net.itarray.automotion.tools.http.connections.ConnectionFactory delegatee;

    public ConnectionFactory(String url) {
        delegatee = new net.itarray.automotion.tools.http.connections.ConnectionFactory(url);
    }

    public CloseableHttpResponse sendPostWithFullResponse(Map map, String endpoint, String token, boolean withMediaFile) throws IOException {
        return delegatee.sendPostWithFullResponse(map, endpoint, token, withMediaFile);
    }

    public Map<Integer, String> sendPost(Map map, String endpoint, String token, boolean withMediaFile) throws IOException {
        return delegatee.sendPost(map, endpoint, token, withMediaFile);
    }

    public Map<Integer, String> sendPost(Map map, Map headers, String endpoint, String token, boolean withMediaFile) throws IOException {
        return delegatee.sendPost(map, headers, endpoint, token, withMediaFile);
    }

    public Map<Integer, String> sendGet(String endpoint, String token) throws IOException {
        return delegatee.sendGet(endpoint, token);
    }

    public Map<Integer, String> sendGet(String endpoint, Map headers) throws IOException {
        return delegatee.sendGet(endpoint, headers);
    }

    public Map<Integer, String> sendPut(Map map, String endpoint, String token) throws IOException {
        return delegatee.sendPut(map, endpoint, token);
    }

    public Map<Integer, String> sendDelete(String endpoint, String token) throws IOException {
        return delegatee.sendDelete(endpoint, token);
    }
}
