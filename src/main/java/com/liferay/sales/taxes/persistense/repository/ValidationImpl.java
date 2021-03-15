package com.liferay.sales.taxes.persistense.repository;

import com.liferay.sales.taxes.persistense.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidationImpl implements ValidationRepository {

    @Override
    public void validate(ProductEntity product) throws Exception {

        isNotNull(product);
        propertiesArePopulated(product);
        notNegativeValue(product);

    }

    private void notNegativeValue (ProductEntity product) throws Exception {
        if( product.getPrice() < 0 || product.getQuantity() < 0) {
            throw new
                    Exception("Price or Quantity properties can't be negative");
        }
    }

    private void propertiesArePopulated(ProductEntity product) throws Exception {
        if (product.getCategory() == null || product.getImported() == null || product.getNameProduct() == null || product.getQuantity() == null  || product.getPrice() == null){
            throw new
                    Exception("Product properties are not Populated");
        }

    }

    private void isNotNull(ProductEntity product) throws Exception {
        if (product == null) {
            throw new
                    Exception("Product is null");
        }
    }

}
