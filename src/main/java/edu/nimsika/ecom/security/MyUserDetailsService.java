package edu.nimsika.ecom.security;

import edu.nimsika.ecom.model.UserRegisterEntity;
import edu.nimsika.ecom.model.UserPrincipal;
import edu.nimsika.ecom.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Primary
@Slf4j
public class MyUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserRegisterEntity user = userRepository.findByUsername(username);

        if (user == null) {
            log.info("User NOT FOUND in Database!");
            throw new UsernameNotFoundException("User not Found!");
        }

        log.info("User FOUND: " + user.getUsername());
        log.info("User DB Password (Hash): " + user.getPassword());

        return new UserPrincipal(user);
    }
}
