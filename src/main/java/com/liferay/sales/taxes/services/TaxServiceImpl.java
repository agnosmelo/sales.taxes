
package com.liferay.sales.taxes.services;

import com.liferay.sales.taxes.domain.model.ProductModel;
import com.liferay.sales.taxes.domain.model.ProductResponse;
import com.liferay.sales.taxes.domain.model.TaxResponse;
import com.liferay.sales.taxes.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

import static java.math.BigDecimal.ZERO;

@Service
public class TaxServiceImpl implements TaxService {

    private static final BigDecimal basicTax = BigDecimal.valueOf(0.1);
    private static final BigDecimal importedTax = BigDecimal.valueOf(0.05);
    private static final BigDecimal roundRule = BigDecimal.valueOf(0.05);

    @Override
    public TaxResponse responseCalculate(Collection<ProductModel> products) throws BadRequestException {
        TaxResponse taxResponse = new TaxResponse();
        BigDecimal finalSalesTax = ZERO;
        BigDecimal finalSumProducts = ZERO;
        for (ProductModel product : products) {

            ValidationUtils.validate(product);
            BigDecimal taxCalculate = calculate(product);
            finalSalesTax = finalSalesTax.add(taxCalculate);

            BigDecimal responseProductsValue = BigDecimal.valueOf(product.getQuantity()).multiply(taxCalculate.add(product.getPrice()));
            finalSumProducts = finalSumProducts.add(responseProductsValue);

            ProductResponse productResponse = new ProductResponse();
            productResponse.setNameProduct(product.getNameProduct());
            productResponse.setPrice(responseProductsValue);
            productResponse.setQuantity(product.getQuantity());

            taxResponse.getProductResponses().add(productResponse);
        }

        taxResponse.setTotal(finalSumProducts);
        taxResponse.setSalesTaxes(finalSalesTax);
        return taxResponse;
    }

    private BigDecimal calculate(ProductModel productModel) {

        BigDecimal calculateTaxPercentage = calculateTaxPercentage(productModel);
        BigDecimal priceAfterTaxes = productModel.getPrice().multiply(calculateTaxPercentage);

        return priceAfterTaxes.divide(roundRule, 0, BigDecimal.ROUND_UP).multiply(roundRule);

    }

    private BigDecimal calculateTaxPercentage(ProductModel product) {
        BigDecimal tax = BigDecimal.valueOf(0.0);

        if (product.getImported()) {
            tax = tax.add(importedTax) ;
        }
        if (product.getCategory().isTaxable()) {
            tax = tax.add(basicTax);
        }
        return tax;

    }

}
