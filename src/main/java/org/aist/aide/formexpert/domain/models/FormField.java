package org.aist.aide.formexpert.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormField {
    private String label;
    private String mLabel;
    private Mapping mapping;
    private String type;
    private String value;
    private List<Abstraction> abstractions;
    private String abstraction;

    public FormField(String label, String mLabel, Mapping mapping, String type, String value, List<Abstraction> abstractions) {
        this.label = label;
        this.mLabel = mLabel;
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

    public String getmLabel() {
        return mLabel;
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

    public void setmLabel(String mLabel) {
        this.mLabel = mLabel;
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
