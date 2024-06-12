package com.mftplus.ecommerce.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());
        http
                .addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/product", "/auth/register", "/auth/login"
                                ,"/error","/", "/auth/verify","/category/**")
                        .permitAll()

                        .requestMatchers("/admin")
                            .hasAuthority("admin")

                        .requestMatchers("/auth/forgot","/auth/reset",
                                "/websocket","/websocket/**","/order")
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
                ,"/css/**", "/js/**");
    }

}
