package org.stupidstick;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataType {
    INTEGER("Integer"),
    DECIMAL("Float"),
    STRING("String");

    private final String name;
}
