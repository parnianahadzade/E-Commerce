package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mftplus.ecommerce.model.entity.enums.Size;

import java.io.IOException;

public class SizeSerializer extends StdSerializer<Size> {

    public SizeSerializer() {
        this(null);
    }

    public SizeSerializer(Class<Size> t) {
        super(t);
    }

    @Override
    public void serialize(Size size, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(size.getTitle());
    }
}
