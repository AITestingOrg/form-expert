package org.aist.aide.formexpert.service.api.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.aist.aide.formexpert.domain.models.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class ApiHttpTemplate<T, K> {
    protected static final Logger logger = LoggerFactory.getLogger(ApiHttpTemplate.class);
    protected Services service;
    protected int port;
    protected Class<T> type;
    protected String prefix;

    @Value("${aist.use_ssl}")
    protected boolean useSsl;

    protected RestTemplate restTemplate;

    public ApiHttpTemplate(RestTemplate restTemplate) {
        port = -1;
        this.restTemplate = restTemplate;
    }

    protected T getOne(String path) {
        String uri = buildUri(path);
        logger.info("Sending a GET request to " + uri);
        ResponseEntity<String> exchange = this.restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            String.class);
        logger.info("Got a reply back");
        logger.info("Reply: " + exchange);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(exchange.getBody(), type);
    }

    protected List<T> getMany(String path) {
        String uri = buildUri(path);
        logger.info("Sending a GET request to " + uri);
        ResponseEntity<List<T>> exchange = this.restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<T>>() {});
        return exchange.getBody();
    }

    protected T create(String path, K obj) {
        String uri = buildUri(path);
        logger.info("Sending a POST request to " + uri);
        logger.info("obj to send: " + obj);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<K> entity = new HttpEntity<>(obj, headers);
        logger.info("entity to send: " + entity);
        ResponseEntity<T> exchange = this.restTemplate.exchange(
            uri,
            HttpMethod.POST,
            entity,
            type);
        logger.info("Got a reply back");
        logger.info("Reply: " + exchange);
        return exchange.getBody();
    }

    protected void update(String path, K obj) {
        String uri = buildUri(path);
        logger.info("Sending a PUT request to " + uri);
        this.restTemplate.put(uri, obj);
    }

    protected void delete(String path) {
        String uri = buildUri(path);
        logger.info("Sending a PUT request to " + uri);
        this.restTemplate.delete(uri);
    }

    protected String buildUri(String path) {
        // this method does not use URI since the app_name is usually not a valid URI Hostname
        StringBuilder url = new StringBuilder();
        if (useSsl) {
            url.append("https://");
        } else {
            url.append("http://");
        }
        url.append(service.name().toLowerCase());
        if (port > 0) {
            url.append(":").append(port);
        }
        if (prefix != null) {
            if (prefix.charAt(0) != '/') {
                url.append("/");
            }
            url.append(prefix);
        }
        if (path.charAt(0) != '/' && url.charAt(url.length() - 1) != '/') {
            url.append("/");
        }
        url.append(path);

        return url.toString();
    }
}
