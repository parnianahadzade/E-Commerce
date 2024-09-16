package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mftplus.ecommerce.model.entity.enums.OrderStatus;

import java.io.IOException;

public class OrderStatusSerializer extends StdSerializer<OrderStatus> {

    public OrderStatusSerializer() {
        this(null);
    }

    public OrderStatusSerializer(Class<OrderStatus> t) {
        super(t);
    }

    @Override
    public void serialize(OrderStatus orderStatus, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(orderStatus.getTitle());
    }
}
