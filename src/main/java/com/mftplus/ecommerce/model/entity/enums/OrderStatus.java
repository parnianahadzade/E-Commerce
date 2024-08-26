package com.mftplus.ecommerce.model.entity.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    waitingForPayment("در انتظار پرداخت"),
    successfulPayOrValidated("تایید شده"),
    failedPayOrCanceled("لغو شده"),
    delivered("تحویل داده شده")
    ;

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }
}
