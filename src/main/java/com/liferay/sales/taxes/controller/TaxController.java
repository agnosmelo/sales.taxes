package com.liferay.sales.taxes.controller;

import com.liferay.sales.taxes.persistense.entity.ProductEntity;
import com.liferay.sales.taxes.persistense.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TaxController {

    private static final String MEDIA_TYPE = "application/json";

    @Autowired
    private TaxRepository taxRepository;

    @RequestMapping(value = "/calculateTax", method = {RequestMethod.POST},
            consumes = {MEDIA_TYPE}, produces = {MEDIA_TYPE})
    public String calculateTax(@RequestBody Collection<ProductEntity> productModel) {

        String finalValue = taxRepository.responseCalculate(productModel);

        return finalValue;
    }

}
