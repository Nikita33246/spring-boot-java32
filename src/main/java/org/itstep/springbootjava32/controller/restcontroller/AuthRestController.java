package org.itstep.springbootjava32.controller.restcontroller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.itstep.springbootjava32.repository.UserRepository;
import org.itstep.springbootjava32.security.jwt.JwtTokenProvider;
import org.itstep.springbootjava32.security.jwt.requests.LoginRequest;
import org.itstep.springbootjava32.security.jwt.requests.SignupRequest;
import org.itstep.springbootjava32.security.jwt.responses.JwtTokenSuccessResponse;
import org.itstep.springbootjava32.service.UserService;
import org.itstep.springbootjava32.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthRestController {


    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final ResponseErrorValidation validation;

    @Autowired
    public AuthRestController(JwtTokenProvider jwtTokenProvider, UserService userService, AuthenticationManager authenticationManager, ResponseErrorValidation validation) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.validation = validation;
    }


    @PostMapping("/signin")
    public ResponseEntity<Object> authenticationUser(@RequestBody LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwt = "Bearer " + jwtTokenProvider.generateToken(authenticate);
        return ResponseEntity.ok(new JwtTokenSuccessResponse(true, jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors = validation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)){
            return errors;
        }
        userService.createUser(signupRequest);

        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7); // Remove the "Bearer " prefix
            response.setHeader("Authorization", jwtToken);
            return ResponseEntity.ok("Logout successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Authorization header");
    }



}
