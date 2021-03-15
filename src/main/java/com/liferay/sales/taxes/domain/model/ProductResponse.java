package com.liferay.sales.taxes.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private String nameProduct;
    private Integer quantity;
    private BigDecimal price;

}
