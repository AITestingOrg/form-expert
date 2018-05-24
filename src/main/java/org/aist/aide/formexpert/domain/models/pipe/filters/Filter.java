package org.aist.aide.formexpert.domain.models.pipe.filters;

import java.util.function.Function;

public class Filter<T> {
    private Function<T, T> operation;

    public Filter(Function<T, T> operation) {
        this.operation = operation;
    }

    public T run(T obj) {
        return operation.apply(obj);
    }
}
