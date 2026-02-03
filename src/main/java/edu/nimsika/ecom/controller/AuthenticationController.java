package edu.nimsika.ecom.controller;

import edu.nimsika.ecom.DTO.AuthenticationReqDTO;
import edu.nimsika.ecom.DTO.AuthenticationResDTO;
import edu.nimsika.ecom.DTO.UserRegisterRequestDto;
import edu.nimsika.ecom.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<Map<String,String>> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto,HttpServletResponse httpServletResponse){
      AuthenticationResDTO authenticationResDTO = authenticationService.register(userRegisterRequestDto);
      Cookie cookie = new Cookie("accessToken",authenticationResDTO.getToken());

      cookie.setHttpOnly(true);
      cookie.setSecure(false);
      cookie.setPath("/");
      cookie.setMaxAge(60*2);
      httpServletResponse.addCookie(cookie);
      Map<String, String> response = new HashMap<>();
      response.put("message", "Login ok");
      response.put("status", "success");

      return ResponseEntity.ok(response);
  }
  @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationReqDTO authenticationReqDTO , HttpServletResponse httpServletResponse){
      AuthenticationResDTO authenticationResDTO = authenticationService.authenticate(authenticationReqDTO);

      Cookie cookie = new Cookie("accessToken",authenticationResDTO.getToken());

      cookie.setHttpOnly(true);
      cookie.setSecure(false);
      cookie.setPath("/");
      cookie.setMaxAge(60*10);

      httpServletResponse.addCookie(cookie);

      return ResponseEntity.ok("Login ok");
  }
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", null);

        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        Map<String, String>  responseBody= new HashMap<>();
        responseBody.put("message", "Logout Successful");
        responseBody.put("status", "success");
        return ResponseEntity.ok(responseBody);
    }


  @GetMapping("/user-profile")
    public ResponseEntity<Map<String, String>> getUserProfile(){
      Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
      String role = authentication.getAuthorities().stream()
              .findFirst()
              .map(GrantedAuthority::getAuthority)
              .orElse("USER");

      Map<String, String> response = new HashMap<>();
      response.put("username", username);
      response.put("role", role);
      response.put("status", "active");
      return ResponseEntity.ok(response);
  }

}
