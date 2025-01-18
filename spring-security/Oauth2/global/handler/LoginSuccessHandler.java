package SpringSecurityOauth2.global.handler;

import SpringSecurityOauth2.domain.oauth2.entity.OAuth2ProviderUser;
import SpringSecurityOauth2.domain.oauth2.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final CookieService cookieService;
    @Value(value = "${server.front}")
    private String domain;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2ProviderUser user = (OAuth2ProviderUser) authentication.getPrincipal();

        String uuid = user.getUuid();
        List<? extends GrantedAuthority> role = user.getAuthorities();

        cookieService.authenticate(uuid, role, response);

        String url = UriComponentsBuilder.fromUriString(domain)
                .path("")
                .build()
                .toUriString();

        response.sendRedirect(url);
    }
}
