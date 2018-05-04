package org.aist.formexpert.controllers;

import java.util.Arrays;
import java.util.List;

import org.aist.formexpert.services.DateClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
public class AbstractorController {
    @Autowired
    private DateClassifierService dateClassifierService;

    private static final String[] supported = {"VALID_FIRST_NAME", "VALID_LAST_NAME", "VALID_EMAIL", "VALID_DATE"};

    @RequestMapping(value = "/supported", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getSupportedAbstractions() {

        return new ResponseEntity<>(Arrays.asList(supported), HttpStatus.ACCEPTED);
    }
}
