package org.aist.aide.formexpert.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormField {
    private String label;
    private String multiplexLabel;
    private Mapping mapping;
    private String type;
    private String value;
    private List<Abstraction> abstractions;
    private String abstraction;

    public FormField(
            String label,
            String multiplexLabel,
            Mapping mapping,
            String type,
            String value,
            List<Abstraction> abstractions) {
        this.label = label;
        this.multiplexLabel = multiplexLabel;
        this.mapping = mapping;
        this.type = type;
        this.value = value;
        this.abstractions = abstractions;
    }

    public void addAbstraction(Abstraction abstraction) {
        abstractions.add(abstraction);
    }

    public String getAbstraction() {
        return abstraction;
    }

    public void setAbstraction(String abstraction) {
        this.abstraction = abstraction;
    }

    public String getLabel() {
        return label;
    }

    public String getMultiplexLabel() {
        return multiplexLabel;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public List<Abstraction> getAbstractions() {
        return abstractions;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setMultiplexLabel(String multiplexLabel) {
        this.multiplexLabel = multiplexLabel;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setAbstractions(List<Abstraction> abstractions) {
        this.abstractions = abstractions;
    }
}
