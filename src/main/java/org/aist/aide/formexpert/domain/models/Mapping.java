package org.aist.aide.formexpert.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mapping implements Serializable {
    private long id;

    @NotBlank
    private String label;

    @NotBlank
    private String type;

    private TreeSet<Classifier> classifiers;

    private String defaultAbstration;

    public Mapping(long id, @NotBlank String label, @NotBlank String type, TreeSet<Classifier> classifiers, String defaultAbstration) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.classifiers = classifiers;
        this.defaultAbstration = defaultAbstration;
    }

    public Mapping(@NotBlank String label, @NotBlank String type) {
        this.label = label;
        this.type = type;
    }

    public String getDefaultAbstraction() {
        return defaultAbstration;
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

    public Set<Classifier> getClassifiers() {
        return classifiers;
    }
}
