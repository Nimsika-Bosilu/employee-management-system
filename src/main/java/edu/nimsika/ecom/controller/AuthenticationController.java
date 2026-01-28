package edu.nimsika.ecom.controller;

import edu.nimsika.ecom.DTO.AuthenticationReqDTO;
import edu.nimsika.ecom.DTO.AuthenticationResDTO;
import edu.nimsika.ecom.DTO.UserRegisterRequestDto;
import edu.nimsika.ecom.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto,HttpServletResponse httpServletResponse){
      AuthenticationResDTO authenticationResDTO = authenticationService.register(userRegisterRequestDto);
      Cookie cookie = new Cookie("accessToken",authenticationResDTO.getToken());

      cookie.setHttpOnly(true);
      cookie.setSecure(false);
      cookie.setPath("/");
      cookie.setMaxAge(60*2);

      httpServletResponse.addCookie(cookie);
      return ResponseEntity.ok("Registration Successfully Completed.");
  }
  @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationReqDTO authenticationReqDTO , HttpServletResponse httpServletResponse){
      AuthenticationResDTO authenticationResDTO = authenticationService.authenticate(authenticationReqDTO);

      Cookie cookie = new Cookie("accessToken",authenticationResDTO.getToken());

      cookie.setHttpOnly(true);
      cookie.setSecure(false);
      cookie.setPath("/");
      cookie.setMaxAge(60*2);

      httpServletResponse.addCookie(cookie);

      return ResponseEntity.ok("Login ok");
  }

  @GetMapping("/user-profile")
    public ResponseEntity<String> getUserProfile(){
      String username = SecurityContextHolder.getContext().getAuthentication().getName();
      return ResponseEntity.ok("User is logged in as: " + username );
  }

}
