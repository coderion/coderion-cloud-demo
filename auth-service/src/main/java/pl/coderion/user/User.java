package pl.coderion.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (C) Coderion sp. z o.o.
 */
@Data
@Builder
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String id;

    @NotNull
    private Boolean active;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private Set<RoleEnum> roles = new HashSet<>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active.booleanValue();
    }

    @Override
    public boolean isAccountNonLocked() {
        return active.booleanValue();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active.booleanValue();
    }

    @Override
    public boolean isEnabled() {
        return active.booleanValue();
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}