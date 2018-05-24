package org.aist.aide.formexpert.domain.models;

import java.util.List;

public class FormField {
    private String label;
    private String mLabel;
    private List<Mapping> mappings;
    private String type;
    private String value;
    private List<Abstraction> abstractions;

    public FormField(String label, String mLabel, List<Mapping> mappings, String type, String value, List<Abstraction> abstractions) {
        this.label = label;
        this.mLabel = mLabel;
        this.mappings = mappings;
        this.type = type;
        this.value = value;
        this.abstractions = abstractions;
    }

    public String getLabel() {
        return label;
    }

    public String getmLabel() {
        return mLabel;
    }

    public List<Mapping> getMappings() {
        return mappings;
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

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
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
