package com.mftplus.ecommerce.api.security;

import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.messaging.access.intercept.AuthorizationChannelInterceptor;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Map;
import java.util.Objects;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {


    private final ApplicationContext context;

    private final JWTRequestFilter jwtRequestFilter;

    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    private final UserService userService;

    public WebSocketConfiguration(ApplicationContext context, JWTRequestFilter jwtRequestFilter, UserService userService) {
        this.context = context;
        this.jwtRequestFilter = jwtRequestFilter;
        this.userService = userService;
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

        registration.interceptors(jwtRequestFilter, authInterceptor,
                new RejectClientMessagesOnChannelInterceptor(),
                new DestinationLevelAuthorizationChannelInterceptor());
    }



    //rejecting clients to send messages to specific topics
    private class RejectClientMessagesOnChannelInterceptor implements ChannelInterceptor {

        private final String[] paths = new String[] {
                "/topic/user/*/address"
        };

        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
          if (Objects.equals(message.getHeaders().get("simpMessageType"), SimpMessageType.MESSAGE)) {

              String destination = (String) message.getHeaders().get(
                      "simpDestination");

              for (String path : paths) {
                  if (MATCHER.match(path, destination))
                      message = null;
              }
          }

          return message;
        }
    }



    //if you are the user you can see the updates for your page
    private class DestinationLevelAuthorizationChannelInterceptor implements ChannelInterceptor {
        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
            if (Objects.equals(message.getHeaders().get("simpMessageType"), SimpMessageType.SUBSCRIBE)) {

                String destination = (String) message.getHeaders().get(
                        "simpDestination");

                Map<String, String> params = MATCHER.extractUriTemplateVariables(
                        "/topic/user/{userId}/**", destination);


                try {
                    Long userId = Long.valueOf(params.get("userId"));

                    Authentication authentication =
                            SecurityContextHolder.getContext().getAuthentication();

                    if (authentication != null){
                        User user = (User) authentication.getPrincipal();

                        if (!userService.userHasPermissionToUser(user, userId)){
                            message = null;
                        }
                    } else {
                        message = null;
                    }

                }catch (NumberFormatException exception){
                    message = null;
                }
            }
            return message;
        }
    }


}
