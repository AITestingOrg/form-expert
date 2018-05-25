package org.aist.aide.formexpert.domain.factories;

import java.util.HashMap;
import java.util.logging.Logger;

import org.aist.aide.formexpert.domain.models.Abstraction;
import org.aist.aide.formexpert.domain.models.Form;
import org.aist.aide.formexpert.domain.models.Mapping;
import org.aist.aide.formexpert.domain.models.Services;
import org.aist.aide.formexpert.domain.models.pipe.filters.Filter;
import org.aist.aide.formexpert.service.api.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ClassifiersFactory {
    private static final Logger LOGGER = Logger.getLogger(ClassifiersFactory.class.getName());

    private LabelMultiplexerTemplate multiplexerTemplate;
    private GeneralTypeTemplate generalTypeTemplate;
    private MappingTemplate mappingTemplate;
    private SpaCyTemplate spaCyTemplate;
    private FiniteHorizonTemplate finiteHorizonTemplate;

    public ClassifiersFactory(
            @Autowired LabelMultiplexerTemplate multiplexerTemplate,
            @Autowired GeneralTypeTemplate generalTypeTemplate,
            @Autowired MappingTemplate mappingTemplate,
            @Autowired SpaCyTemplate spaCyTemplate,
            @Autowired FiniteHorizonTemplate finiteHorizonTemplate) {
        this.multiplexerTemplate = multiplexerTemplate;
        this.generalTypeTemplate = generalTypeTemplate;
        this.mappingTemplate = mappingTemplate;
        this.spaCyTemplate = spaCyTemplate;
        this.finiteHorizonTemplate = finiteHorizonTemplate;
    }

    public Filter<Form> createLabelMultiplexFilter() {
        return new Filter<>(form -> {
            for (var field: form.getFields()) {
                try {
                    var label = multiplexerTemplate.multiplexLabel(field.getLabel());
                    field.setMultiplexLabel(label);
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
            for (var field: form.getFields()) {
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

    public Filter<Form> createSpaCyFilter() {
        return new Filter<>(form -> {
            for (var field: form.getFields()) {
                try {
                    var result = spaCyTemplate.getAbstraction(field);
                    field.getAbstractions().forEach(abstraction -> {
                        if (abstraction.getService() == Services.SPACYSERVICE) {
                            abstraction.setAbstraction(result);
                        }
                    });
                } catch (Exception e) {
                    LOGGER.severe(e.getStackTrace().toString());
                    return null;
                }
            }
            return form;
        });
    }

    public Filter<Form> createFiniteHorizonFilter() {
        return new Filter<>(form -> {
            for (var field: form.getFields()) {
                try {
                    var result = finiteHorizonTemplate.getAbstraction(field);
                    field.getAbstractions().forEach(abstraction -> {
                        if (abstraction.getService() == Services.FINITEHORIZONSERVICE) {
                            abstraction.setAbstraction(result);
                        }
                    });
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
            for (var field: form.getFields()) {
                try {
                    var mapping = mappingTemplate.getKnownMapping(
                            new Mapping(
                                    field.getMultiplexLabel(),
                                    field.getType()));
                    field.setMapping(mapping);
                    field.addAbstraction(new Abstraction(mapping.getService(), mapping.getAbstraction()));
                } catch (HttpClientErrorException e) {
                    if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                        LOGGER.severe(e.getStackTrace().toString());
                        return form;
                    }
                } catch (Exception e) {
                    LOGGER.severe(e.getStackTrace().toString());
                    return null;
                }
            }
            return form;
        });
    }

    public Filter<Form> createAbstractionReducer() {
        return new Filter<>(form -> {
            var abstractionMap = new HashMap<String, String>();
            for (var field: form.getFields()) {
                field.getAbstractions().forEach(abstraction -> {
                    if (abstraction.getAbstraction() != null && !abstraction.getAbstraction().isEmpty()) {
                        abstractionMap.put(abstraction.getService().toString(), abstraction.getAbstraction());
                    }
                });
                // todo find best service and update abstraction for field
            }
            return form;
        });
    }
}
