package com.liferay.sales.taxes.services;

import com.liferay.sales.taxes.domain.enums.CategoryEnum;
import com.liferay.sales.taxes.domain.model.ProductModel;
import com.liferay.sales.taxes.domain.model.ProductResponse;
import com.liferay.sales.taxes.domain.model.TaxResponse;
import com.liferay.sales.taxes.mother.ProductMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.liferay.sales.taxes.mother.ProductMother.createProductModel;
import static org.junit.jupiter.api.Assertions.*;

@Import(TaxServiceImpl.class)
class TaxServiceImplTest {

    @Autowired
    private TaxService taxService;

    @Test
    void basico(){
        org.junit.jupiter.api.Assertions.assertEquals(1,1);
    }

    @Test
    void basicTaxResponseCalculateCategoryNonTaxable() throws Exception{
        //Prepara
        ProductModel product = createProductModel(CategoryEnum.BOOKS,"Book",BigDecimal.valueOf(12.49),1,false);
        //Roda
        TaxResponse result = taxService.responseCalculate(Collections.singletonList(product));

        //Verifica
        ProductResponse productResponse = new ProductResponse();
        productResponse.setQuantity(1);
        productResponse.setPrice(BigDecimal.valueOf(12.49));
        productResponse.setNameProduct("book");
        Assertions.assertThat(result.getMessage()).isNull();
        Assertions.assertThat(result.getSalesTaxes()).isEqualTo(BigDecimal.valueOf(0.0));
        Assertions.assertThat(result.getTotal()).isEqualTo(BigDecimal.valueOf(12.49));
        Assertions.assertThat(result.getProductResponses()).contains(productResponse);

    }
   /*
    @Test
    void basicTaxResponseCalculateCategoryTaxable() throws Exception{

        ProductModel product = createProductModel(CategoryEnum.OTHER,"Book",12.49,1,false);

        String result = taxService.responseCalculate(Collections.singletonList(product));

        //Assertions.assertThat(result).isEqualTo()
    }

    @Test
    void basicTaxResponseCalculateImported() throws Exception{

        ProductModel product = createProductModel(CategoryEnum.BOOKS,"Book",12.49,1,true);

        String result = taxService.responseCalculate(Collections.singletonList(product));

        //Assertions.assertThat(result).isEqualTo()
    }

    @Test
    void basicTaxResponseCalculateNotImported() throws Exception{

        ProductModel product = createProductModel(CategoryEnum.BOOKS,"Book",12.49,1,false);

        String result = taxService.responseCalculate(Collections.singletonList(product));

        //Assertions.assertThat(result).isEqualTo()
    }

    @Test
    void basicTaxResponseCalculateNotNegativeValue() throws Exception{

        ProductModel product = createProductModel(CategoryEnum.BOOKS,"Book",12.49,1,false);

        String result = taxService.responseCalculate(Collections.singletonList(product));

        //Assertions.assertThat(result).isEqualTo()
    }


*/

}