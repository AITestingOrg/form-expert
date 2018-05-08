package org.aist.formexpert.models;

public class AbstractionResponse {
    private String abstraction;
    private String target;

    public AbstractionResponse(String abstraction, String target) {
        this.abstraction = abstraction;
        this.target = target;
    }

    public String getAbstraction() {
        return abstraction;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return String.format("{abstraction: %s, target: %s}", abstraction, target);

    }
}
