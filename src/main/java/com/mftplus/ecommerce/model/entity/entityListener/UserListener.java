package com.mftplus.ecommerce.model.entity.entityListener;

import com.mftplus.ecommerce.model.entity.LocalUser;
import jakarta.persistence.*;

public class UserListener {
    @PrePersist
    public void prePersist(LocalUser user) {

    }

    @PreUpdate
    public void preUpdate(LocalUser user) {

    }

    @PreRemove
    public void preRemove(LocalUser user) {

    }

    @PostLoad
    public void postLoad(LocalUser user) {

    }

    @PostRemove
    public void postRemove(LocalUser user) {

    }

    @PostUpdate
    public void postUpdate(LocalUser user) {

    }

    @PostPersist
    public void postPersist(LocalUser user) {

    }
}
