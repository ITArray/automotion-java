package net.itarray.automotion.tools.http.connections;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RestClient {

    private static final Logger LOG = Logger.getLogger(RestClient.class.getName());
    private Map<String, String> headers;
    private String body;
    private String baseUrl;

    public RestClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.headers = new HashMap();
    }

    public RestClient withHeader(String header, String value) {
        this.headers.put(header, value);
        return this;
    }

    public RestClient withBody(String body) {
        this.body = body;
        return this;
    }

    public HttpResponse post(String endpointUrl) {
        try {
            HttpClient httpClient = getHttpClientBuilder().build();
            String uri = buildUrl(endpointUrl);
            HttpPost request = new HttpPost(uri);
            for (Map.Entry<String, String> map : headers.entrySet()) {
                request.addHeader(map.getKey(), map.getValue());
            }
            StringEntity params = new StringEntity(body, "UTF-8");
            request.setEntity(params);

            CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);


            Map<Integer, String> mapResult = new HashMap<>();

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String value = String.valueOf(getBodyResponse(httpResponse));
            mapResult.put(statusCode, value);

            log("POST", uri, body, statusCode, value);
            return new HttpResponse(statusCode, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse get(String endpointUrl) {
        try {
            HttpClient httpClient = getHttpClientBuilder().build();
            String uri = buildUrl(endpointUrl);
            HttpGet request = new HttpGet(uri);

            for (Map.Entry<String, String> map : headers.entrySet()) {
                request.addHeader(map.getKey(), map.getValue());
            }

            CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);

            Map<Integer, String> mapResult = new HashMap<>();

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String value = String.valueOf(getBodyResponse(httpResponse));
            mapResult.put(statusCode, value);

            log("GET", uri, "", statusCode, value);

            return new HttpResponse(statusCode, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse delete(String endpointUrl) {
        try {
            HttpClient httpClient = getHttpClientBuilder().build();
            String uri = buildUrl(endpointUrl);
            HttpDelete request = new HttpDelete(uri);

            for (Map.Entry<String, String> map : headers.entrySet()) {
                request.addHeader(map.getKey(), map.getValue());
            }

            CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);

            Map<Integer, String> mapResult = new HashMap<>();

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String value = String.valueOf(getBodyResponse(httpResponse));
            mapResult.put(statusCode, value);

            log("DELETE", uri, "", statusCode, value);

            return new HttpResponse(statusCode, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse update(String endpointUrl) {
        try {
            HttpClient httpClient = getHttpClientBuilder().build();
            String uri = buildUrl(endpointUrl);
            HttpPatch request = new HttpPatch(uri);

            for (Map.Entry<String, String> map : headers.entrySet()) {
                request.addHeader(map.getKey(), map.getValue());
            }

            StringEntity params = new StringEntity(body, "UTF-8");
            request.setEntity(params);

            CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);

            Map<Integer, String> mapResult = new HashMap<>();

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String value = String.valueOf(getBodyResponse(httpResponse));
            mapResult.put(statusCode, value);

            log("PATCH", uri, body, statusCode, value);

            return new HttpResponse(statusCode, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String buildUrl(String endpointUrl) throws URISyntaxException {
        String[] split = baseUrl.split("://");
        String scheme = split.length > 0 ? split[0] : "http";
        String host = split.length > 0 ? split[1] : baseUrl;
        URIBuilder builder = new URIBuilder();
        builder.setScheme(scheme);
        builder.setHost(host);
        builder.setPath(endpointUrl);
        return builder.build().toString();

    }

    private void log(String requestType, String uri, String body, int responseCode, String responseBody) {
        LOG.info("------------------------------------------- START ---------------------------------------");
        LOG.info("*** " + requestType + ": " + uri);
        if (!body.equals("")) {
            LOG.info("*** Request Body" + ": " + body);
        }
        LOG.info("*** Response Code: " + responseCode);
        LOG.info("*** Response Body: " + responseBody);
        LOG.info("-------------------------------------------- END ----------------------------------------\n");
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
}