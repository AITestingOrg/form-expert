package org.aist.formexpert.models;

public class AbstractionRequest {
    private String label;
    private String value;

    public AbstractionRequest(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("{label: %s, value: %s}", label, value);

    }
}
