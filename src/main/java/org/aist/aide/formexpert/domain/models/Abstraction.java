package org.aist.aide.formexpert.domain.models;

public class Abstraction {
    private final Services service;
    private String abstraction;

    public Abstraction(Services service, String abstraction) {
        this.service = service;
        this.abstraction = abstraction;
    }

    public String getAbstraction() {
        return abstraction;
    }

    public Services getService() {
        return service;
    }

    public void setAbstraction(String abstraction) {
        this.abstraction = abstraction;
    }
}
