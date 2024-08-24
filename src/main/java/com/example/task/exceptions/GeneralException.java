package com.example.task.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GeneralException extends Exception{
    private final ExceptionData exceptionData;

    public GeneralException(String msg, ErrorCode errorCode) {
       super(msg);
       exceptionData = new ExceptionData(msg,errorCode.getId());
    }

    public GeneralException(String msg, ErrorCode errorCode, Throwable cause)
    {
        super(msg, cause);
        exceptionData = new ExceptionData(msg, errorCode.getId());
    }
}

