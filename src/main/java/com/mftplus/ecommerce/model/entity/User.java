package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@Entity(name = "userEntity")
@Table(name = "user_tbl")
public class User extends Base implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.UserInfo.class)
    private Long id;

    @Column(name = "u_username", nullable = false, unique = true, length = 50)
    @JsonView(Views.UserInfo.class)
    private String username;


    //todo validation here causes problem
    @JsonIgnore
    @Column(name = "u_password", nullable = false)
    private String password;

    @Column(name = "u_email", unique = true, length = 320)
    @JsonView(Views.UserInfo.class)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id desc")
    private List<VerificationToken> verificationTokens = new ArrayList<>();

    @Column(name = "email_verified", nullable = false)
    @JsonView(Views.UserInfo.class)
    private Boolean emailVerified = false;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn (name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn (name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @Column(name = "u_is_identified", nullable = false)
    @JsonView(Views.UserInfo.class)
    private boolean isIdentified;

    @OneToOne(mappedBy = "user")
    @JsonView(Views.UserInfo.class)
    private Person person;


    //for user details
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        this.getRoles().forEach(role -> {authorityList.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorityList;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(){

    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.verificationTokens = user.getVerificationTokens();
        this.emailVerified = user.getEmailVerified();
        this.roles = user.getRoles();
        this.person = user.getPerson();
        this.isIdentified = user.isIdentified;
    }
}