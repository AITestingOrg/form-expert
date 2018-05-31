package org.aist.aide.formexpert.service.api.http;

import org.aist.aide.formexpert.common.models.FormField;
import org.aist.aide.formexpert.domain.models.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class FiniteHorizonTemplate extends ApiHttpTemplate<String, FormField> {
    public FiniteHorizonTemplate(@Autowired RestTemplate restTemplate) {
        super(restTemplate);
        service = Services.FINITEHORIZONSERVICE;
        port = 8080;
        type = String.class;
        prefix = "api/v1/finitetype";
    }

    public String getAbstraction(FormField field) {
        String type;
        try {
            return this.getOne("");
        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}
