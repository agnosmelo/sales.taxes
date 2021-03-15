package com.liferay.sales.taxes.services;

import com.liferay.sales.taxes.domain.model.ProductModel;
import com.liferay.sales.taxes.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidationImpl implements ValidationService {

    @Override
    public void validate(ProductModel product) throws BadRequestException {

        isNotNull(product);
        propertiesArePopulated(product);
        notNegativeValue(product);

    }

    private void notNegativeValue (ProductModel product) throws BadRequestException {

        if( product.getPrice().compareTo(BigDecimal.ZERO) < 0 || product.getQuantity() < 0) {
            throw new
                    BadRequestException("Price or Quantity properties can't be negative");
        }
    }

    private void propertiesArePopulated(ProductModel product) throws BadRequestException {
        if (product.getCategory() == null || product.getImported() == null || product.getNameProduct() == null || product.getQuantity() == null  || product.getPrice() == null){
            throw new
                    BadRequestException("Product properties are not Populated");
        }

    }

    private void isNotNull(ProductModel product) throws BadRequestException {
        if (product == null) {
            throw new
                    BadRequestException("Product is null");
        }
    }

}
