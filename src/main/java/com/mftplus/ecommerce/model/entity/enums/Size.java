package com.mftplus.ecommerce.model.entity.enums;

import lombok.Getter;

@Getter
public enum Size {
    S("S"),
    XS("XS"),
    L("L"),
    XL("XL"),
    XL2("2XL"),
    XL3("3XL"),
    M("M")
    ;

    private final String title;

    Size(String title) {
        this.title = title;
    }

}