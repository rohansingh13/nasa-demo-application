package com.example.demobackend.external.nasa.service;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@Service
public class NasaApiService {

    @Value("${nasa.asteroid.list.api.url}")
    private String nasaAstroidListApiUrl;

    @Value("${nasa.asteroid.details.api.url}")
    private String nasaAstroidDetailsApiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    public static final String DEFAULT_ENCODING = "UTF-8";

    public String getAsteroidsForWeek(String startDate, String endDate) throws IOException, URISyntaxException {
        String url = buildAsteroidListUrl(startDate, endDate);
        return doGet(url);
    }

    public String getAsteroidDetailsById(Long asteroidId) throws IOException {
        String url = buildAsteroidDetailsUrl(asteroidId);
        return doGet(url);
    }

    private String buildAsteroidListUrl(String startDate, String endDate) throws URISyntaxException {
        return new URIBuilder(nasaAstroidListApiUrl)
                .addParameter("start_date", startDate)
                .addParameter("end_date", endDate)
                .addParameter("api_key", apiKey)
                .build()
                .toString();
    }

    private String buildAsteroidDetailsUrl(Long asteroidId) {
        return nasaAstroidDetailsApiUrl + "/" + asteroidId + "?api_key=" + apiKey;
    }

    private String doGet(String url) throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            return getStringResponse(client, httpGet);
        }
    }

    private String doPost(String request, String url)
            throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(request, StandardCharsets.UTF_8));
            return getStringResponse(client, httpPost);
        }
    }

    private String getStringResponse(CloseableHttpClient client, HttpUriRequest request) throws IOException {
        CloseableHttpResponse response = client.execute(request);
        Header contentEncoding = response.getEntity().getContentEncoding();
        String encoding = contentEncoding == null ? DEFAULT_ENCODING : contentEncoding.getValue();

        return IOUtils.toString(response.getEntity().getContent(), encoding);
    }

}
