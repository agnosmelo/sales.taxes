package com.liferay.sales.taxes.persistense.repository;

import com.liferay.sales.taxes.persistense.entity.ProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
public interface ValidationRepository {

    void validate(@Valid ProductEntity product) throws Exception;
}
