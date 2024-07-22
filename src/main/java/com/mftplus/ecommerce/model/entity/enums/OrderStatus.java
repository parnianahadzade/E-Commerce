package com.mftplus.ecommerce.model.entity.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    paying("در حال پرداخت"),
    successfulPay("پرداخت موفق"),
    failedPay("پرداخت ناموفق")
    ;

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }
}
