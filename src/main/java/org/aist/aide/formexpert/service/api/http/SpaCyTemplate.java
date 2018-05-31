package org.aist.aide.formexpert.service.api.http;

import org.aist.aide.formexpert.common.models.FormField;
import org.aist.aide.formexpert.domain.models.Services;
import org.aist.aide.formexpert.domain.models.SpaCyAbstractionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class SpaCyTemplate extends ApiHttpTemplate<SpaCyAbstractionDto, FormField> {
    public SpaCyTemplate(@Autowired RestTemplate restTemplate) {
        super(restTemplate);
        service = Services.SPACYSERVICE;
        port = 8080;
        type = SpaCyAbstractionDto.class;
        prefix = "api/v1/abstractions";
    }

    public String getAbstraction(FormField field) {
        String type;
        try {
            return this.create("", field);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}
