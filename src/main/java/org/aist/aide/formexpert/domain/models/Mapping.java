package org.aist.aide.formexpert.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mapping implements Serializable {
    private long id;

    @NotBlank
    private String label;

    @NotBlank
    private String type;

    private String abstraction;

    private Services service;

    public Mapping() {
    }

    public Mapping(@NotBlank String label, @NotBlank String type, String abstraction) {
        this.label = label;
        this.type = type;
        this.abstraction = abstraction;
    }

    public Mapping(long id, @NotBlank String label, @NotBlank String type, String abstraction) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.abstraction = abstraction;
    }

    public Mapping(@NotBlank String label, @NotBlank String type) {
        this.label = label;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public String getAbstraction() {
        return abstraction;
    }

    public Services getService() {
        return service;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAbstraction(String abstraction) {
        this.abstraction = abstraction;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public boolean compareTo(Mapping that) {
        if (!this.type.equals(that.getType())) {
            return false;
        }
        if (!this.label.equals(that.getLabel())) {
            return false;
        }
        return this.abstraction.equals(that.getAbstraction());
    }
}
