package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@MappedSuperclass
public class Base {
    // TODO: 7/30/2024 version id

//      @Version
//    @JsonbTransient
//    private  Long versionId;

    @JsonIgnore
    private boolean deleted;
}
