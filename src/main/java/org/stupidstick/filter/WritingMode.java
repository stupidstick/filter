package org.stupidstick.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WritingMode {
    APPEND(true),
    REWRITE(false);

    private final boolean append;
}
