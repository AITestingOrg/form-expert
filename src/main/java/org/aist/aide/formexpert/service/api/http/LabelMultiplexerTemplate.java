package org.aist.aide.formexpert.service.api.http;

import org.aist.aide.formexpert.common.models.Label;
import org.aist.aide.formexpert.domain.models.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class LabelMultiplexerTemplate extends ApiHttpTemplate<Label, Label> {
    public LabelMultiplexerTemplate(@Autowired RestTemplate restTemplate) {
        super(restTemplate);
        service = Services.LABELMULTIPLEXER;
        port = 8080;
        type = Label.class;
        prefix = "api/v1/in-label";
    }

    public String multiplexLabel(String labelValue) throws Exception {
        Label label;
        try {
            label = this.getOne("name/" + labelValue);
            return label.getLabel().getName();
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                label = new Label(labelValue);
                this.create("", label);
                return null;
            }
            throw new Exception(String.format("Something is wrong with teh Label Multiplexer, got \"%s\"", labelValue));
        }
    }
}
