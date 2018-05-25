package org.aist.aide.formexpert.domain.models;

public class SpaCyAbstractionDto {
    private String target;
    private String abstraction;

    public SpaCyAbstractionDto(String target, String abstraction) {
        this.target = target;
        this.abstraction = abstraction;
    }

    public SpaCyAbstractionDto() {
    }

    public String getTarget() {
        return target;
    }

    public String getAbstraction() {
        return abstraction;
    }
}
