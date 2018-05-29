package org.aist.aide.formexpert.service.api.http;

import org.aist.aide.formexpert.common.models.FormField;
import org.aist.aide.formexpert.domain.models.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WarehouseTemplate extends ApiHttpTemplate<String, FormField> {
    public WarehouseTemplate(@Autowired RestTemplate restTemplate) {
        super(restTemplate);
        service = Services.FORMEXPERTWAREHOUSE;
        port = 8080;
        type = String.class;
        prefix = "api/v1/unlabelled";
    }

    public String createRow(FormField field) {
        try {
            return this.create("", field);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}