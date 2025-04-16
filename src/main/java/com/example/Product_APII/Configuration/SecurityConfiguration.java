package com.example.Product_APII.Configuration;

import com.example.Product_APII.Repository.IUserSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String[] PUBLIC_ENDPOINTS = {
            "/auth/**", "/mail/**","/permissions",
            "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
            "/api/categories/**","/api/products","/api/wishlists/**",
            "/api/payments/**","/api/deliveries/**","/api/orders/**",
            "/api/cart/**","/api/order-detail/**","/api/reviews/**",
            "api/order-reviews/**"
    };

    private static final String[] PUBLIC_GET_ENDPOINTS = {
            "/uploads/**"
    };

    private static final String[] SECURITY_ENDPOINTS = {
            "/users/**","/roles/**"
    };

    private static final String SECRET_KEY = "f!92SN9R8KmbvWrR!VOFhL8u?RoFAAu9awbCuu=qwEIdpjRiD-L0Qxh-20gACItM";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(IUserSecurityService iUserSecurityService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(iUserSecurityService);
        provider.setPasswordEncoder(passwordEncoder()); // Đảm bảo bạn đã định nghĩa PasswordEncoder
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
                        .requestMatchers(SECURITY_ENDPOINTS).hasRole("ADMIN")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(jwtDecoder())
//                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
                                )
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA512");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }

//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(this::extractAuthorities);
//        return converter;
//    }
//
//    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
//        List<String> roles = jwt.getClaimAsStringList("roles");
//        if (roles == null) {
//            return List.of();
//        }
//        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
//    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
