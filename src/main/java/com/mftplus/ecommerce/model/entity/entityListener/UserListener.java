package com.mftplus.ecommerce.model.entity.entityListener;

import com.mftplus.ecommerce.model.entity.Person;
import jakarta.persistence.*;

public class UserListener {
    @PrePersist
    public void prePersist(Person user) {

    }

    @PreUpdate
    public void preUpdate(Person user) {

    }

    @PreRemove
    public void preRemove(Person user) {

    }

    @PostLoad
    public void postLoad(Person user) {

    }

    @PostRemove
    public void postRemove(Person user) {

    }

    @PostUpdate
    public void postUpdate(Person user) {

    }

    @PostPersist
    public void postPersist(Person user) {

    }
}
