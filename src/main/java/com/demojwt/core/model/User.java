package com.demojwt.core.model;

import com.demojwt.core.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends PersistedObject implements UserDetails {

    public enum UserStatus {
        ACTIVE, BLOCKED, INACTIVE, PENDING_ACTIVATION, DELETED
    }
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private UserStatus status;
    private String address;
    //comma separated list of all old passwords (for the time being its kept in a single column)
    @Column(length = 1000)
    private String oldPasswords;
    private Date expiryDate;
    private int numberOfLoginAttempts;
    private int numberOfPasswordChangeAttempts;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date lastLogout;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date lastLogin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastPasswordChange;
    @JsonIgnore
    private String authPin;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled2FA;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean passwordExpiryEnabled;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id")
    )
    private List<Group> groups;
    @Transient
    private List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
