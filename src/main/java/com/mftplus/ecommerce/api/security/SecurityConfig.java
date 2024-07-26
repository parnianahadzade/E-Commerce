package com.mftplus.ecommerce.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    private final JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable());
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http
                .addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/product","/search", "/api/auth/register", "/api/auth/login"
                                ,"/error","/", "/api/auth/verify","/api/category/**",
                                "/product/**","/api/product/{id}","/cart/**")
                        .permitAll()

                        .requestMatchers("/admin","/api/product/save")
                            .hasAuthority("admin")

                        .requestMatchers("/api/auth/forgot","/api/auth/reset",
                                "/websocket","/websocket/**","/api/order")
                                        .hasAuthority("user")

                        .anyRequest().authenticated()
                )
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/product","/auth/register","/auth/login","/",
//                                "/auth/verify","/test/data","/error","/auth/forgot",
//                                "/auth/reset","/websocket", "/websocket/**")
//                        .permitAll()
//                        .anyRequest().authenticated()
//                )

                .logout(
                        (logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
        );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/images/**","/productImages/**"
                ,"/css/**", "/js/**","/dist/**","/assets/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
