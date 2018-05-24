package org.aist.aide.formexpert.domain.models;

import java.util.List;

public class Form {
    private final List<FormField> fields;

    public Form(List<FormField> fields) {
        this.fields = fields;
    }

    public List<FormField> getFields() {
        return fields;
    }
}
