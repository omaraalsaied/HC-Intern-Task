package com.example.task.exceptions;

import lombok.Getter;

public enum ErrorCode {
    ENTITY_IS_REQUIRED(1),
    NAME_IS_REQUIRED(2),
    ID_NOT_FOUND(3),
    MISSING_PROPERTIES(4),;

    @Getter
    private final int id;

    ErrorCode(final int id)
    {
        this.id = id;
    }
}
