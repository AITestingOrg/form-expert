package org.aist.formexpert.services;

import org.aist.formexpert.models.AbstractionRequest;
import org.aist.formexpert.models.AbstractionResponse;
import org.aist.formexpert.models.Services;
import org.springframework.stereotype.Service;

@Service
public class AbstractorService extends ApiService<AbstractionResponse, AbstractionRequest> {
    public AbstractorService() {
        service = Services.ABSTRACTOR;
        port = 8080;
        type = AbstractionResponse.class;
    }

    public AbstractionResponse getAbstraction(AbstractionRequest abstractionRequest) {
        return create("abstraction/", abstractionRequest);
    }
}
