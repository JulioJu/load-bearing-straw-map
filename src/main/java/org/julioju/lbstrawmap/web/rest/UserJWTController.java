package org.julioju.lbstrawmap.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import org.julioju.lbstrawmap.security.jwt.JWTFilter;
import org.julioju.lbstrawmap.security.jwt.TokenProvider;
import org.julioju.lbstrawmap.web.rest.vm.LoginVM;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // START added by JulioJu
    private final org.julioju.lbstrawmap.repository.UserRepository userRepository;
    private final org.julioju.lbstrawmap.repository.AuthHistoryRepository authHistoryRepository;

    public UserJWTController(
        TokenProvider tokenProvider,
        AuthenticationManagerBuilder authenticationManagerBuilder,
        org.julioju.lbstrawmap.repository.UserRepository userRepository,
        org.julioju.lbstrawmap.repository.AuthHistoryRepository authHistoryRepository
    ) {
        this.userRepository = userRepository;
        this.authHistoryRepository = authHistoryRepository;
        // END added by JulioJu
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginVM.getUsername(),
            loginVM.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // START added by JulioJu
        // Not very optimized, but well.
        System.out.println("loginVM.getUsername(): " + loginVM.getUsername());
        var user = userRepository.findOneByLogin(loginVM.getUsername());
        if (user.isEmpty()) {
            user = userRepository.findOneByEmailIgnoreCase(loginVM.getUsername());
        }
        var authHistory = new org.julioju.lbstrawmap.domain.AuthHistory();
        authHistory.setUserId(user.get().getId());
        authHistory.setDate(java.time.Instant.now());
        authHistoryRepository.save(authHistory);
        // END added by JulioJu

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginVM.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
