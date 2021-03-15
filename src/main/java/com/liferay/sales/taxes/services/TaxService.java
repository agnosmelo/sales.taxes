package com.liferay.sales.taxes.services;

import com.liferay.sales.taxes.domain.model.ProductModel;
import com.liferay.sales.taxes.domain.model.TaxResponse;
import com.liferay.sales.taxes.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface TaxService {

    TaxResponse responseCalculate(Collection<ProductModel> productEntities) throws BadRequestException;

}
