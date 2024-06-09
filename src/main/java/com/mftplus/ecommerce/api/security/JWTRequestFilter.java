package com.mftplus.ecommerce.api.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.UserRepository;
import com.mftplus.ecommerce.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
public class JWTRequestFilter extends OncePerRequestFilter implements ChannelInterceptor {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public JWTRequestFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //token is in header
        String tokenHeader = request.getHeader("Authorization");

        UsernamePasswordAuthenticationToken token = checkToken(tokenHeader);

        if (token != null){
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        }

        filterChain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken checkToken(String token){
        if (token != null && token.startsWith("Bearer ")) {
            //we do not need "Bearer "
            token = token.substring(7);

            // .decode throws jwtDecodeException
            try {
                String username = jwtService.getUsername(token);
                Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(username);

                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();

                    if (user.getEmailVerified()) {
                        //inputs : authentication principal = user object , password , granted authorities
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        return authenticationToken;
                    }
                }
            } catch (JWTDecodeException exception) {

            }
        }

        SecurityContextHolder.getContext().setAuthentication(null);
        return null;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        SimpMessageType messageType =
                (SimpMessageType) message.getHeaders().get("simpMessageType");

        if (messageType.equals(SimpMessageType.SUBSCRIBE)
                || messageType.equals(SimpMessageType.MESSAGE)) {
            //websocket headers
            Map nativeHeaders = (Map) message.getHeaders().get("nativeHeaders");

            if (nativeHeaders != null) {
                //now we get the authorization from that native header
                List authTokenList = (List) nativeHeaders.get("Authorization");

                if (authTokenList != null) {
                    String tokenHeader = (String) authTokenList.get(0);
                    checkToken(tokenHeader);
                }
            }
        }

        return message;
    }
}
