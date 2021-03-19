package com.liferay.sales.taxes.persistense.entity;

import com.liferay.sales.taxes.domain.enums.CategoryEnum;
import lombok.Getter;
import lombok.Setter;

public class ProductEntity {

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private String nameProduct;

    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private Boolean imported;

    @Getter
    @Setter
    private CategoryEnum category;



}
