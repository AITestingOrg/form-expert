package org.aist.formexpert.models;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SupportedAbstractions {
    VALID_FIRST_NAME,
    VALID_LAST_NAME,
    VALID_EMAIL,
    VALID_DATE,
    VALID_ADDRESS,
    INVALID;

    public static List<String> getSupported() {
        return Stream.of(values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
