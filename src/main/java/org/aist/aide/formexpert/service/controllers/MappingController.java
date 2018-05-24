package org.aist.aide.formexpert.service.controllers;

import javax.validation.Valid;
import org.aist.aide.formexpert.domain.models.Mapping;
import org.aist.aide.formexpert.service.api.http.MappingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Component
@RequestMapping("api/v1/form")
public class MappingController {
    private MappingTemplate mappingTemplate;

    public MappingController(@Autowired MappingTemplate mappingTemplate) {
        this.mappingTemplate = mappingTemplate;
    }

    @RequestMapping("/mapping/{label}/{type}")
    public ResponseEntity getMapping(@PathVariable String label, @PathVariable String type) {
        try {
            var mapping = mappingTemplate.getOne(String.format("/%s/%s/", label, type));
            return new ResponseEntity<>(mapping, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getStatusCode());
        }
    }

    @RequestMapping("/mapping/known")
    public ResponseEntity getAllMappings() {
        var mappings = mappingTemplate.getKnown("/");
        return new ResponseEntity<>(mappings, HttpStatus.OK);
    }

    @PostMapping("/mapping/unknown")
    public ResponseEntity createMapping(@Valid @RequestBody Mapping mapping) {
        try {
            var created = mappingTemplate.create("/", mapping);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getStatusCode());
        }
    }

    @PutMapping("/mapping/")
    public ResponseEntity updateMapping(@Valid @RequestBody Mapping mapping) {
        try {
            mappingTemplate.update("/", mapping);
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getStatusCode());
        }
    }
}
