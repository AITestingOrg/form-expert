package org.aist.aide.formexpert.domain.models;

public class Abstraction {
    private final String service;
    private final String abstraction;

    public Abstraction(String service, String abstraction) {
        this.service = service;
        this.abstraction = abstraction;
    }

    public String getAbstraction() {
        return abstraction;
    }

    public String getService() {
        return service;
    }
}
