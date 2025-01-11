package SpringSecurityOauth2.global.config.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import SpringSecurityOauth2.domain.oauth2.service.OAuth2UserService;
import SpringSecurityOauth2.global.handler.LoginSuccessHandler;
import SpringSecurityOauth2.global.handler.OauthAccessDeniedHandler;
import SpringSecurityOauth2.global.handler.OauthAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class Oauth2ClientConfig {

    private final SecurityCorsConfig corsConfig;
    private final OAuth2UserService oAuth2UserService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final OauthAccessDeniedHandler oauthAccessDeniedHandler;
    private final OauthAuthenticationEntryPoint oauthAuthenticationEntryPoint;
    @Value(value = "${server.error-page}")
    private String errorPage;

    @Bean
    SecurityFilterChain securityFilterChane(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests ->
                requests.requestMatchers("/**", "/api-docs/**", "/swagger-ui/**").permitAll()
        );

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource())); // CORS 설정 활성화

        http.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(oAuth2UserService))
                .successHandler(loginSuccessHandler)
                .failureUrl(errorPage)
        );

        http.exceptionHandling(exception -> exception
                .accessDeniedHandler(oauthAccessDeniedHandler)
                .authenticationEntryPoint(oauthAuthenticationEntryPoint)
        );

        return http.build();
    }
}
