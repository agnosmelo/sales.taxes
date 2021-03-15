package com.liferay.sales.taxes.controller;

import com.liferay.sales.taxes.domain.model.ProductModel;
import com.liferay.sales.taxes.domain.model.TaxResponse;
import com.liferay.sales.taxes.exception.BadRequestException;
import com.liferay.sales.taxes.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TaxController {

    private static final String MEDIA_TYPE = "application/json";

    @Autowired
    private TaxService taxService;

    @RequestMapping(value = "/calculateTax", method = {RequestMethod.POST},
            consumes = {MEDIA_TYPE}, produces = {MEDIA_TYPE})
    public ResponseEntity<TaxResponse> calculateTax(@RequestBody Collection<ProductModel> productModel) {
        TaxResponse finalValue = new TaxResponse();

        try {
            finalValue = taxService.responseCalculate(productModel);
        } catch (BadRequestException e){
            finalValue.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(finalValue);
        }

        return ResponseEntity.ok(finalValue);
    }



}
