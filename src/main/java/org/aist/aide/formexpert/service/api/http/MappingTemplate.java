package org.aist.aide.formexpert.service.api.http;

import org.aist.aide.formexpert.domain.models.Mapping;
import org.aist.aide.formexpert.domain.models.Services;
import org.aist.aide.formexpert.service.api.http.ApiHttpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingTemplate extends ApiHttpTemplate<Mapping, Mapping> {
    public MappingTemplate(@Autowired RestTemplate restTemplate) {
        super(restTemplate);
        service = Services.MAPPINGSERVICE;
        port = 8080;
        type = Mapping.class;
        prefix = "api/v1/mapping";
    }

    public List<Mapping> getKnown(String path) {
        var mappings = getMany(path);
        return mappings.stream().filter(x -> x.getAbstraction() != null)
                                .collect(Collectors.toList());
    }

    @Override
    public List<Mapping> getMany(String path) {
        String uri = buildUri(path);
        ResponseEntity<Mapping[]> exchange = this.restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                Mapping[].class);

        return Arrays.asList(exchange.getBody());
    }
}