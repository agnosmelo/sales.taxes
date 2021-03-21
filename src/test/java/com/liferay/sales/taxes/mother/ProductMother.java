package com.liferay.sales.taxes.mother;

import com.liferay.sales.taxes.domain.enums.CategoryEnum;
import com.liferay.sales.taxes.domain.model.ProductModel;

import java.math.BigDecimal;

public class ProductMother {

    private ProductMother() {
    }

    public static ProductModel createProductModel(CategoryEnum categoryEnum, String nameProduct, BigDecimal price, Integer quantity, boolean imported) {

        return ProductModel.builder()
                .category(categoryEnum)
                .nameProduct(nameProduct)
                .price(price)
                .quantity(quantity)
                .imported(imported)
                .build();
    }

}
