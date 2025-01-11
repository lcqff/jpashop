package SpringSecurityOauth2.domain.oauth2.service;

import SpringSecurityOauth2.domain.member.entity.Member;
import SpringSecurityOauth2.domain.member.service.MemberService;
import SpringSecurityOauth2.domain.oauth2.entity.GoogleUser;
import SpringSecurityOauth2.domain.oauth2.entity.ProviderUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final MemberService memberService;
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2User oAuth2User = super.loadUser(userRequest); //사용자 정보 반환
        ProviderUser providerUser = providerUser(clientRegistration, oAuth2User);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Member member = memberService.registerOrReturn(registrationId, providerUser);
        log.info("회원가입 = " + providerUser.getName());
        providerUser.setUuid(member.getUuid());
        providerUser.setRole(member.getRole());
        return providerUser;
    }

    private ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User) {
        String registrationId = clientRegistration.getRegistrationId();
        if (registrationId.equals("google")) {
            return new GoogleUser(oAuth2User, clientRegistration);
        }
        return null; //todo: 예외 반환
    }
}
