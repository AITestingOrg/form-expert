package org.aist.formexpert.controllers;

import static org.aist.formexpert.models.Labels.addressLabels;
import static org.aist.formexpert.models.Labels.dateLabels;
import static org.aist.formexpert.models.SupportedAbstractions.getSupported;

import java.util.List;
import org.aist.formexpert.models.AbstractionRequest;
import org.aist.formexpert.models.AbstractionResponse;
import org.aist.formexpert.models.SupportedAbstractions;
import org.aist.formexpert.services.AbstractorService;
import org.aist.formexpert.services.AddressValidatorService;
import org.aist.formexpert.services.DateClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
public class AbstractorController {

    @Autowired
    private AbstractorService abstractorService;

    @Autowired
    private DateClassifierService dateClassifierService;

    @Autowired
    private AddressValidatorService addressValidatorService;

    @RequestMapping(value = "supported", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getSupportedAbstractions() {
        return new ResponseEntity<>(getSupported(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "abstraction", method = RequestMethod.POST)
    public ResponseEntity<AbstractionResponse> getAbstraction(@RequestBody AbstractionRequest abstractionRequest) {
        if (isRequestOfLabel(abstractionRequest, dateLabels)) {
            String value = abstractionRequest.getValue();
            boolean isValidDate = dateClassifierService.isValidDate(value);
            SupportedAbstractions abstraction = isValidDate
                    ? SupportedAbstractions.VALID_DATE : SupportedAbstractions.INVALID;

            AbstractionResponse abstractionResponse = new AbstractionResponse(abstraction.name(), value);
            return new ResponseEntity<>(abstractionResponse, HttpStatus.OK);
        }
        if (isRequestOfLabel(abstractionRequest, addressLabels)) {
            String value = abstractionRequest.getValue();
            boolean isValidAddress = addressValidatorService.isValid(value);
            SupportedAbstractions abstraction = isValidAddress
                    ? SupportedAbstractions.VALID_ADDRESS : SupportedAbstractions.INVALID;

            AbstractionResponse abstractionResponse = new AbstractionResponse(abstraction.name(), value);
            return new ResponseEntity<>(abstractionResponse, HttpStatus.OK);
        }

        AbstractionResponse reply = abstractorService.getAbstraction(abstractionRequest);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    private boolean isRequestOfLabel(AbstractionRequest abstractionRequest, String[] labels) {
        final String requestLabel = abstractionRequest.getLabel();
        for (String label: labels) {
            if (label.equalsIgnoreCase(requestLabel)) {
                return true;
            }
        }
        return false;
    }
}
