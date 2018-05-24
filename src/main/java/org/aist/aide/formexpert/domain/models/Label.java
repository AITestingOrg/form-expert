package org.aist.aide.formexpert.domain.models;

public class Label {
    private final String name;
    private Label label;

    public Label(String name, Label label) {
        this.name = name;
        this.label = label;
    }

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Label getLabel() {
        return label;
    }
}
