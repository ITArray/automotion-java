package http.connections;

import http.helpers.Helper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.json.simple.JSONObject;

import javax.net.ssl.SSLContext;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

class ConnectionBuilder {
    
    public ConnectionBuilder(String url) {
        this.url = url;
    }
    
    private String url;

    public CloseableHttpResponse sendPOST(Map<Object, Object> map, String endpoint, String token, boolean withMediaFile) throws IOException {
        HttpClientBuilder httpClientBuilder = getHttpClientBuilder();

        HttpClient httpClient = httpClientBuilder.build();

        System.out.println("\n\n------------------------------------------------------------");
        System.out.println("POST request to: " + url + endpoint);
        HttpPost request = new HttpPost(url + endpoint);
        request.addHeader("Authorization", "Bearer " + token);
        request.addHeader("Authorization", token);
        request.addHeader("authorization", token);
        request.addHeader("Cookie", "AuthCookie=" + token);
        String fileName = null;
        File file = null;
        if (withMediaFile) {
            String boundary = "-------------" + System.currentTimeMillis();
            fileName = (String) map.get("file");

            try {
                file = Helper.createFile(fileName);

            } catch (AWTException e) {
                e.printStackTrace();
            }

            MultipartEntityBuilder entity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setBoundary(boundary);

            if (fileName != null) {
                entity.addPart("file", new ByteArrayBody(Files.readAllBytes(file.toPath()), fileName));
            }

            for (Map.Entry entry: map.entrySet()){
                entity.addPart(String.valueOf(entry.getKey()), new StringBody((String) entry.getValue()));
            }


            request.setEntity(entity.build());
            request.setHeader("Content-type", "multipart/form-data; boundary=" + boundary);
        } else {
            StringEntity params = new StringEntity(JSONObject.toJSONString(map));
            System.out.println("with data: " + JSONObject.toJSONString(map));
            request.addHeader("Content-Type", "application/json;charset=UTF-8");
            request.setEntity(params);
        }

        CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);

        if (file != null) {
            file.delete();
        }

        return httpResponse;
    }

    private HttpClientBuilder getHttpClientBuilder() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).useTLS().build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);


        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setSSLSocketFactory(sslConnectionFactory);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslConnectionFactory)
                .register("http", new PlainConnectionSocketFactory())
                .build();

        HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(registry);
        httpClientBuilder.setConnectionManager(ccm);
        return httpClientBuilder;
    }

    private CloseableHttpResponse sendGET(String endpoint, String token) throws IOException {
        HttpClientBuilder httpClientBuilder = getHttpClientBuilder();

        HttpClient httpClient = httpClientBuilder.build();

        System.out.println("GET request to: " + url + endpoint);
        HttpGet request = new HttpGet(url + endpoint);
        //request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Authorization", "Bearer " + token);


        CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);

        return httpResponse;
    }

    private CloseableHttpResponse sendDELETE(String endpoint, String token) throws IOException {
        HttpClientBuilder httpClientBuilder = getHttpClientBuilder();

        HttpClient httpClient = httpClientBuilder.build();

        System.out.println("DELETE request to: " + url + endpoint);
        HttpDelete request = new HttpDelete(url + endpoint);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Authorization", "Bearer " + token);


        CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);

        return httpResponse;
    }

    private CloseableHttpResponse sendPUT(Map<String, String> map, String endpoint, String token) throws IOException {
        HttpClientBuilder httpClientBuilder = getHttpClientBuilder();

        HttpClient httpClient = httpClientBuilder.build();

        System.out.println("PUT request to: " + url + endpoint);
        HttpPut request = new HttpPut(url + endpoint);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Authorization", "Bearer " + token);

        StringEntity params = new StringEntity(JSONObject.toJSONString(map));
        System.out.println("with data: " + JSONObject.toJSONString(map));

        request.setEntity(params);

        CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);

        return httpResponse;
    }

    protected Map<Integer, String> getPOST(Map map, String endpoint, String token, boolean withMediaFile) throws IOException {
        Map<Integer, String> mapResult = new HashMap<Integer, String>();

        CloseableHttpResponse response = sendPOST(map, endpoint, token, withMediaFile);

        StringBuffer body = getBodyResponse(response);

        mapResult.put(response.getStatusLine().getStatusCode(), body.toString());

        System.out.println("\n\nResponse:");
        System.out.println(response.getStatusLine());
        System.out.println(body.toString());

        return mapResult;
    }

    protected Map<Integer, String> getGET(String endpoint, String token) throws IOException {
        Map<Integer, String> mapResult = new HashMap<Integer, String>();

        CloseableHttpResponse response = sendGET(endpoint, token);

        StringBuffer body = getBodyResponse(response);

        mapResult.put(response.getStatusLine().getStatusCode(), body.toString());

        return mapResult;
    }

    protected Map<Integer, String> getPUT(Map map, String endpoint, String token) throws IOException {
        Map<Integer, String> mapResult = new HashMap<Integer, String>();

        CloseableHttpResponse response = sendPUT(map, endpoint, token);

        StringBuffer body = getBodyResponse(response);

        mapResult.put(response.getStatusLine().getStatusCode(), body.toString());

        return mapResult;
    }

    protected Map<Integer, String> getDELETE(String endpoint, String token) throws IOException {
        Map<Integer, String> mapResult = new HashMap<Integer, String>();

        CloseableHttpResponse response = sendDELETE(endpoint, token);

        StringBuffer body = getBodyResponse(response);

        mapResult.put(response.getStatusLine().getStatusCode(), body.toString());

        return mapResult;
    }

    private StringBuffer getBodyResponse(CloseableHttpResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));

        String inputLine;

        StringBuffer body = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            body.append(inputLine);
        }
        reader.close();

        response.close();
        return body;
    }
}
