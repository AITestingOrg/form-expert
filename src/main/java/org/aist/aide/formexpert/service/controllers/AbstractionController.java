package org.aist.aide.formexpert.service.controllers;

import java.util.LinkedList;

import org.aist.aide.formexpert.common.models.Form;
import org.aist.aide.formexpert.domain.factories.ClassifiersFactory;
import org.aist.aide.formexpert.domain.models.pipe.ClassificationPipe;
import org.aist.aide.formexpert.domain.models.pipe.filters.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/abstraction")
public class AbstractionController {
    private ClassifiersFactory classifiersFactory;

    public AbstractionController(@Autowired ClassifiersFactory classifiersFactory) {
        this.classifiersFactory = classifiersFactory;
    }

    @GetMapping("/")
    public ResponseEntity<Form> getAbstraction(@RequestBody Form form) {
        try {
            var queue = new LinkedList<Filter<Form>>();
            queue.add(classifiersFactory.createLabelMultiplexFilter());
            queue.add(classifiersFactory.createTypeFilter());
            queue.add(classifiersFactory.createMappingFilter());
            queue.add(classifiersFactory.createSpaCyFilter());
            queue.add(classifiersFactory.createFiniteHorizonFilter());
            queue.add(classifiersFactory.createAbstractionFieldReducer());
            queue.add(classifiersFactory.createWarehouseFilter());
            var pipe = new ClassificationPipe(queue);
            return new ResponseEntity<>(pipe.exec(form), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
