package com.mftplus.ecommerce.model.entity.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mftplus.ecommerce.model.entity.SizeSerializer;
import lombok.Getter;

@Getter
@JsonSerialize(using = SizeSerializer.class)
public enum Size {
    FREESIZE("FreeSize"),
    XS("XS"),
    S("S"),
    M("M"),
    L("L"),
    XL("XL"),
    XL2("2XL"),
    XL3("3XL")
    ;

    private final String title;

    Size(String title) {
        this.title = title;
    }

//    public static Size findByTitle(String title) {
//        return Arrays.stream(values()).filter(size -> size.getTitle()
//                        .equalsIgnoreCase(title)).findFirst()
//                            .orElseThrow(() -> new IllegalArgumentException("No enum found with title : " + title));
//    }

}
