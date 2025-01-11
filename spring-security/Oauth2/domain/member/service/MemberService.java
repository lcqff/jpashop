package SpringSecurityOauth2.domain.member.service;


import static SpringSecurityOauth2.domain.member.entity.Role.ROLE_USER;

import SpringSecurityOauth2.domain.member.entity.Member;
import SpringSecurityOauth2.domain.member.repository.MemberRepository;
import SpringSecurityOauth2.domain.member.service.dto.response.MemberResponseDto;
import SpringSecurityOauth2.domain.oauth2.entity.ProviderUser;
import SpringSecurityOauth2.global.helper.AuthorizationHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthorizationHelper authorizationHelper;

    @Transactional(readOnly = true)
    public Member findByUuid(String uuid) {
        return memberRepository.findByUuid(uuid).orElseThrow(NoSuchElementException::new);
    }

    public Member registerOrReturn(String registrationId, ProviderUser providerUser) {
        Member existMember = memberRepository.findByEmail(providerUser.getEmail());
        if (existMember != null) { // 이미 가입한 회원 검증
            return existMember;
        }

        Member member = Member.builder()
                .email(providerUser.getEmail())
                .name(providerUser.getName())
                .provider(registrationId)
                .memberImage(providerUser.getPicture()) //todo 기본 이미지 등록
                .role(ROLE_USER)
                .build();

        memberRepository.save(member);
        return member;
    }

    public MemberResponseDto getMyInfo() {
        String uuid = authorizationHelper.getMyUuid();
        log.info("uuid = " + uuid);
        Member member = findByUuid(uuid);
        return MemberResponseDto.from(member);
    }
}
