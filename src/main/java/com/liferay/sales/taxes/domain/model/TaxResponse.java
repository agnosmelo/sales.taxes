package com.liferay.sales.taxes.domain.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaxResponse extends GenericResponse {
    @Getter
    @Setter
    private BigDecimal salesTaxes;

    @Getter
    @Setter
    private BigDecimal total;

    @Getter
    @Setter
    private List<ProductResponse> productResponses = new ArrayList<>();

}
