package org.aist.aide.formexpert.domain.models.pipe;

import org.aist.aide.formexpert.domain.models.Form;
import org.aist.aide.formexpert.domain.models.pipe.filters.Filter;

import java.util.Queue;

public class ClassificationPipe extends Pipe<Form> {
    public ClassificationPipe(Queue<Filter<Form>> queue) {
        super(queue);
    }
}
