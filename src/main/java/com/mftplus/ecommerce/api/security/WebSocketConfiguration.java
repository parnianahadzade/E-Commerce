package com.mftplus.ecommerce.api.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.messaging.access.intercept.AuthorizationChannelInterceptor;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {


    private final ApplicationContext context;

    private final JWTRequestFilter jwtRequestFilter;

    public WebSocketConfiguration(ApplicationContext context, JWTRequestFilter jwtRequestFilter) {
        this.context = context;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //front end uses sockJs
        registry.addEndpoint("/websocket").setAllowedOriginPatterns("**").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    private AuthorizationManager<Message<?>> makeMassageAuthorizationManager() {
        MessageMatcherDelegatingAuthorizationManager.Builder messages =
                new MessageMatcherDelegatingAuthorizationManager.Builder();

        messages
                .simpDestMatchers("/topic/user/**").authenticated()
                .anyMessage().permitAll();

        return messages.build();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        AuthorizationManager<Message<?>> authorizationManager =
                makeMassageAuthorizationManager();

        AuthorizationChannelInterceptor authInterceptor =
                new AuthorizationChannelInterceptor(authorizationManager);

        AuthorizationEventPublisher publisher =
                new SpringAuthorizationEventPublisher(context);

        authInterceptor.setAuthorizationEventPublisher(publisher);

        registration.interceptors(jwtRequestFilter, authInterceptor);
    }
}