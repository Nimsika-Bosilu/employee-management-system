package edu.nimsika.ecom.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

    public class UserPrincipal implements UserDetails {

        private UserRegisterEntity userRegisterEntity;

        public UserPrincipal(UserRegisterEntity userRegisterEntity) {
            this.userRegisterEntity = userRegisterEntity;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singleton(new SimpleGrantedAuthority("hr_manager"));
        }

        @Override
        public String getPassword() {
            return userRegisterEntity.getPassword();
        }

        @Override
        public String getUsername() {
            return userRegisterEntity.getUsername();
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

