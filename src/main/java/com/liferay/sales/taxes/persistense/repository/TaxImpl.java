
package com.liferay.sales.taxes.persistense.repository;

import com.liferay.sales.taxes.persistense.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;

@Component
public class TaxImpl implements TaxRepository{

    private Double basicTax = 0.1;
    private Double importedTax = 0.05;
    private String roundScale = "0.05";
    //DecimalFormat fmt = new DecimalFormat("%.2f");


    @Autowired
    private ValidationRepository validations;

    public TaxImpl() {
    }



    public void TaxServiceImpl(Double basicTax, Double importedTax, String roundScale) {
        this.basicTax = basicTax;
        this.importedTax = importedTax;
        this.roundScale = roundScale;
    }

    @Override
    public String responseCalculate (Collection<ProductEntity> products){
        String responseProduct = "";
        Double taxSale;
        Double produtsAmaunt = 0.0;
        for (ProductEntity product : products) {
            try {
                validations.validate(product);
                Double taxCalculate = calculate(product);
                Double responseProductsValue = taxCalculate + product.getPrice();
                responseProduct = responseProduct + "\""+ product.getNameProduct() +"\":" + responseProductsValue + ",";
                produtsAmaunt = produtsAmaunt + product.getPrice();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        taxSale = calculateTaxSale(products);
        responseProduct = responseProduct + " \" Sales Taxes \":" + taxSale + ",";
        Double sumTotalProduct = produtsAmaunt + taxSale;
        responseProduct = responseProduct + " \" Total \":" + sumTotalProduct;

        return responseProduct;
    }

    @Override
    public Double calculate(ProductEntity productEntity) {

        Double calcTex = calculateTaxAmount(productEntity);
        Double taxAmaunt = productEntity.getPrice() * calcTex;

        BigDecimal scale = new BigDecimal(roundScale);
        BigDecimal finalTaxAmaunt = new BigDecimal(taxAmaunt);

        //Depois de converter para uma valores  mais exatos com Bigdecimal, calculamos a data final.
        finalTaxAmaunt =  finalTaxAmaunt.divide(scale).setScale(0, BigDecimal.ROUND_UP).multiply(scale);

        return finalTaxAmaunt.doubleValue();
    }



    public Double calculateTaxSale (Collection<ProductEntity> products) {

        Double totalTax = 0.0;

        for (ProductEntity product : products){

                Double quantity= new Double(product.getQuantity());
                Double finalTaxAmaunt = calculate(product);
                totalTax = totalTax + (quantity * finalTaxAmaunt);

        }
        return totalTax;
    }



    private Double calculateTaxAmount(ProductEntity product) {
        double tax = 0.0;

        if (product.getImported() == true) {
            tax = tax + importedTax;
        }
        if ( product.getCategory().isTaxable()) {
            tax = tax + basicTax;
        }
        return tax;

    }

}
