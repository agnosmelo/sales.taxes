package com.liferay.sales.taxes.services;

import com.liferay.sales.taxes.domain.model.ProductModel;
import com.liferay.sales.taxes.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
public interface ValidationService {

    void validate(@Valid ProductModel product) throws BadRequestException;
}
