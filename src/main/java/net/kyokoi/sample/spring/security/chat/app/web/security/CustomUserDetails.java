package net.kyokoi.sample.spring.security.chat.app.web.security;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@Builder
@Data
public class CustomUserDetails implements UserDetails {

    private final Collection<GrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return "{noop}test";
    }

    @Override
    public String getUsername() {
        return "test";
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

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
