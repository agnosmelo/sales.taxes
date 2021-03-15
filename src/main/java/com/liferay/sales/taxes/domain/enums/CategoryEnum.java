package com.liferay.sales.taxes.domain.enums;

public enum CategoryEnum {
    BOOKS(false), FOOD(false), MEDICAL_PRODUCTS(false), OTHER(true);

    private boolean taxable;

    CategoryEnum(boolean taxable) {
        this.taxable = taxable;
    }

    public boolean isTaxable() {
        return this.taxable;
    }

}
