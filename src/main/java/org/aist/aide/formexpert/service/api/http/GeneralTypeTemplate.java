package org.aist.aide.formexpert.service.api.http;

import org.aist.aide.formexpert.domain.models.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GeneralTypeTemplate extends ApiHttpTemplate<String, String> {
    public GeneralTypeTemplate(@Autowired RestTemplate restTemplate) {
        super(restTemplate);
        service = Services.GENERALTYPESERVICE;
        port = 8080;
        type = String.class;
        prefix = "api/v1/type";
    }

    public String getType(String value) {
        String type;
        try {
            return this.getOne(value);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}
