package edu.nimsika.ecom.service;

import edu.nimsika.ecom.DTO.AuthenticationReqDTO;
import edu.nimsika.ecom.DTO.AuthenticationResDTO;
import edu.nimsika.ecom.DTO.UserRegisterRequestDto;
import edu.nimsika.ecom.model.UserRegisterEntity;
import edu.nimsika.ecom.repository.UserRepository;
import edu.nimsika.ecom.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationResDTO register(UserRegisterRequestDto userRegisterRequestDto) {
        UserRegisterEntity user=modelMapper.map(userRegisterRequestDto, UserRegisterEntity.class);
        user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResDTO.builder().token(jwtToken).build();
    }

    public AuthenticationResDTO authenticate(AuthenticationReqDTO authenticationReqDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReqDTO.getUsername(),authenticationReqDTO.getPassword()));
        var user=userRepository.findByUsername(authenticationReqDTO.getUsername());
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResDTO.builder()
                .token(jwtToken)
                .build();
    }
}
