package com.mftplus.ecommerce.model.entity.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mftplus.ecommerce.model.entity.OrderStatusSerializer;
import lombok.Getter;

@Getter
@JsonSerialize(using = OrderStatusSerializer.class)
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
