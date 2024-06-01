package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
