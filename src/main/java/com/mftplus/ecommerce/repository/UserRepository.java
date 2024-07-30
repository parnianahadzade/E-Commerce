package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPersonIdAndDeletedFalse(Long id);

    Optional<User> findByUsernameIgnoreCaseAndDeletedFalse(String username);

    Optional<User> findByEmailIgnoreCaseAndDeletedFalse(String email);

    List<User> findAllByDeletedFalse();

    Optional<User> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update userEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
