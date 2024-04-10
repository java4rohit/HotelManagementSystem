package com.lcwd.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    public class SecurityConfiguration {
        @Bean
        public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
            http
                    .authorizeExchange(exchangeSpec -> exchangeSpec
                            .anyExchange().authenticated()
                    )
                    .oauth2Client(withDefaults())
                    .oauth2ResourceServer(oauth2ResourceServerSpec -> oauth2ResourceServerSpec
                            .jwt(withDefaults())
                    );
            return http.build();
        }
    }


}
