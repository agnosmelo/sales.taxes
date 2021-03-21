package com.liferay.sales.taxes.services;

import com.liferay.sales.taxes.domain.enums.CategoryEnum;
import com.liferay.sales.taxes.domain.model.ProductModel;
import com.liferay.sales.taxes.domain.model.ProductResponse;
import com.liferay.sales.taxes.domain.model.TaxResponse;
import com.liferay.sales.taxes.exception.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static com.liferay.sales.taxes.mother.ProductMother.createProductModel;

//import org.junit.jupiter.api.Test;


class TaxServiceImplTest {

    private TaxService taxService;

    @BeforeEach
    public void setup(){
        taxService = new TaxServiceImpl();
    }

    @Test
    public void basicTest() {
        Assertions.assertThat(1).isEqualTo(1);
    }
    @Test
    public void basicTaxResponseCalculateCategoryNonTaxable() throws Exception{

        ProductModel product = createProductModel(CategoryEnum.BOOKS,"Book",BigDecimal.valueOf(12.49),1,false);

        TaxResponse result = taxService.responseCalculate(Collections.singletonList(product));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setQuantity(1);
        productResponse.setPrice(BigDecimal.valueOf(12.49));
        productResponse.setNameProduct("Book");

        Assertions.assertThat(result.getMessage()).isNull();
        Assertions.assertThat(result.getSalesTaxes()).isEqualTo("0.00");
        Assertions.assertThat(result.getTotal()).isEqualTo(BigDecimal.valueOf(12.49));
        Assertions.assertThat(result.getProductResponses()).contains(productResponse);

    }

    @Test
    public void basicTaxResponseCalculateCategoryTaxable() throws Exception{

        ProductModel product = createProductModel(CategoryEnum.OTHER,"Music CD",BigDecimal.valueOf(14.99),1,false);

        TaxResponse result = taxService.responseCalculate(Collections.singletonList(product));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setQuantity(1);
        productResponse.setPrice(BigDecimal.valueOf(16.49));
        productResponse.setNameProduct("Music CD");

        Assertions.assertThat(result.getMessage()).isNull();
        Assertions.assertThat(result.getSalesTaxes()).isEqualTo("1.50");
        Assertions.assertThat(result.getTotal()).isEqualTo(BigDecimal.valueOf(16.49));
        Assertions.assertThat(result.getProductResponses()).contains(productResponse);

    }

    @Test
    public void basicTaxResponseCalculateImported() throws Exception{

        ProductModel product = createProductModel(CategoryEnum.OTHER,"bottle of perfume",BigDecimal.valueOf(47.50),1,true);


        TaxResponse result = taxService.responseCalculate(Collections.singletonList(product));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setQuantity(1);
        productResponse.setPrice(BigDecimal.valueOf(54.65));
        productResponse.setNameProduct("bottle of perfume");

        Assertions.assertThat(result.getMessage()).isNull();
        Assertions.assertThat(result.getSalesTaxes()).isEqualTo("7.15");
        Assertions.assertThat(result.getTotal()).isEqualTo(BigDecimal.valueOf(54.65));
        Assertions.assertThat(result.getProductResponses()).contains(productResponse);

    }

    @Test
    void basicTaxResponseCalculateNotNegativeValue() {

        ProductModel product = createProductModel(CategoryEnum.BOOKS,"Book",BigDecimal.valueOf(-12.49),1,false);

        try {
            TaxResponse result = taxService.responseCalculate(Collections.singletonList(product));
            Assertions.fail("This method show a BadRequestException");
        } catch (BadRequestException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Price or Quantity properties can't be negative");
        }


    }

}