package com.liferay.sales.taxes.persistense.repository;

import com.liferay.sales.taxes.persistense.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface TaxRepository {

    String responseCalculate(Collection<ProductEntity> productEntities);

}
