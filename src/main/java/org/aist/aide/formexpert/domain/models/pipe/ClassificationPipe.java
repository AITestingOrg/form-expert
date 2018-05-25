package org.aist.aide.formexpert.domain.models.pipe;

import java.util.Queue;

import org.aist.aide.formexpert.domain.models.Form;
import org.aist.aide.formexpert.domain.models.pipe.filters.Filter;

public class ClassificationPipe extends Pipe<Form> {
    public ClassificationPipe(Queue<Filter<Form>> queue) {
        super(queue);
    }
}
