package SpringSecurityOauth2.domain.oauth2.entity;

import SpringSecurityOauth2.domain.member.entity.Role;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface ProviderUser extends OAuth2User {

    String getUuid();
    void setUuid(String uuid);
    void setRole(Role role);
    String getId();
    String getEmail();
    String getProvider();
    String getPicture();
}
