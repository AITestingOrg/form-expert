package org.aist.aide.formexpert.domain.factories;

import org.aist.aide.formexpert.domain.models.Abstraction;
import org.aist.aide.formexpert.domain.models.Form;
import org.aist.aide.formexpert.domain.models.Mapping;
import org.aist.aide.formexpert.domain.models.pipe.filters.Filter;
import org.aist.aide.formexpert.service.api.http.GeneralTypeTemplate;
import org.aist.aide.formexpert.service.api.http.LabelMultiplexerTemplate;
import org.aist.aide.formexpert.service.api.http.MappingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.logging.Logger;

@Component
public class ClassifiersFactory {
    private static final Logger LOGGER = Logger.getLogger(ClassifiersFactory.class.getName());

    private LabelMultiplexerTemplate multiplexerTemplate;
    private GeneralTypeTemplate generalTypeTemplate;
    private MappingTemplate mappingTemplate;

    public ClassifiersFactory(@Autowired LabelMultiplexerTemplate multiplexerTemplate, @Autowired GeneralTypeTemplate generalTypeTemplate, @Autowired MappingTemplate mappingTemplate) {
        this.multiplexerTemplate = multiplexerTemplate;
        this.generalTypeTemplate = generalTypeTemplate;
        this.mappingTemplate = mappingTemplate;
    }

    public Filter<Form> createLabelMultiplexFilter() {
        return new Filter<>(form -> {
           for(var field: form.getFields()) {
               try {
                   var label = multiplexerTemplate.multiplexLabel(field.getLabel());
                   field.setmLabel(label);
               } catch (Exception e) {
                   LOGGER.severe(e.getStackTrace().toString());
                   return null;
               }
           }
           return form;
        });
    }

    public Filter<Form> createTypeFilter() {
        return new Filter<>(form -> {
           for(var field: form.getFields()) {
               try {
                   field.setType(generalTypeTemplate.getType(field.getValue()));
               } catch (Exception e) {
                   LOGGER.severe(e.getStackTrace().toString());
                   return null;
               }
           }
           return form;
        });
    }

    public Filter<Form> createMappingFilter() {
        return new Filter<>(form -> {
            for(var field: form.getFields()) {
                try {
                    var mapping = mappingTemplate.getKnownMapping(new Mapping(field.getmLabel(), field.getType()));
                    field.addMapping(mapping);
                    field.addAbstraction(new Abstraction(mapping.getService(), mapping.getAbstraction()));
                } catch (HttpClientErrorException e) {
                    if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                        LOGGER.severe(e.getStackTrace().toString());
                        return null;
                    }
                } catch (Exception e) {
                    LOGGER.severe(e.getStackTrace().toString());
                    return null;
                }
            }
            return form;
        });
    }
}
