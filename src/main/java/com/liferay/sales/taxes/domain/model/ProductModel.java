package com.liferay.sales.taxes.domain.model;

import com.liferay.sales.taxes.domain.enums.CategoryEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
public class ProductModel {

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private String nameProduct;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private Boolean imported;

    @Getter
    @Setter
    private CategoryEnum category;



}
