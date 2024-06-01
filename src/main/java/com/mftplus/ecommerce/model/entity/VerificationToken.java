package com.mftplus.ecommerce.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_token_tbl")
@Entity(name = "verificationTokenEntity")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_token_seq")
    @SequenceGenerator(name = "verification_token_seq",sequenceName = "verification_token_seq",allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    //large object
    //todo : does not build the tbl
//    @Lob
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    //when did we create this jwt
    @Column(name = "created_time_stamp", nullable = false)
    private Timestamp createdTimeStamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}