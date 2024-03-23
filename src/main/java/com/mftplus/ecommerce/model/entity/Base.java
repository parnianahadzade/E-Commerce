package com.mftplus.ecommerce.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
@MappedSuperclass
public class Base {
//      @Version
//    @JsonbTransient
//    private  Long versionId;

//    @JsonbTransient
    private boolean deleted;
}
