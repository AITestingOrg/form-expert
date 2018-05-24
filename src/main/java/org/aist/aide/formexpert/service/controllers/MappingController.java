package org.aist.aide.formexpert.service.controllers;

import javax.validation.Valid;
import org.aist.aide.formexpert.domain.models.Mapping;
import org.aist.aide.formexpert.service.api.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Component
@RequestMapping("api/v1/form")
public class MappingController {
    private MappingService mappingService;

    public MappingController(@Autowired MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @RequestMapping("/mapping/{label}/{type}")
    public ResponseEntity getMapping(@PathVariable String label, @PathVariable String type) {
        try {
            var mapping = mappingService.getOne(String.format("/%s/%s/", label, type));
            return new ResponseEntity<>(mapping, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getStatusCode());
        }
    }

    @RequestMapping("/mapping/known")
    public ResponseEntity getAllMappings() {
        var mappings = mappingService.getKnown("/");
        return new ResponseEntity<>(mappings, HttpStatus.OK);
    }

    @PostMapping("/mapping/unknown")
    public ResponseEntity createMapping(@Valid @RequestBody Mapping mapping) {
        try {
            var created = mappingService.create("/", mapping);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getStatusCode());
        }
    }

    @PutMapping("/mapping/")
    public ResponseEntity updateMapping(@Valid @RequestBody Mapping mapping) {
        try {
            mappingService.update("/", mapping);
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getStatusCode());
        }
    }
}
