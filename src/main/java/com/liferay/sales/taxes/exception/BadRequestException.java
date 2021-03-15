package com.liferay.sales.taxes.exception;

import lombok.AllArgsConstructor;


public class BadRequestException  extends Exception{

    public BadRequestException(String message) {
        super(message);
    }
}
