package com.mftplus.ecommerce.model.entity.entityListener;

import com.mftplus.ecommerce.model.entity.Person;
import jakarta.persistence.*;

public class PersonListener {
    @PrePersist
    public void prePersist(Person person) {

    }

    @PreUpdate
    public void preUpdate(Person person) {

    }

    @PreRemove
    public void preRemove(Person person) {

    }

    @PostLoad
    public void postLoad(Person person) {

    }

    @PostRemove
    public void postRemove(Person person) {

    }

    @PostUpdate
    public void postUpdate(Person person) {

    }

    @PostPersist
    public void postPersist(Person person) {

    }
}
