package com.desafio.taxajuros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class ObjectEmptyException extends RuntimeException {

    public ObjectEmptyException() {
        super();
    }

}
