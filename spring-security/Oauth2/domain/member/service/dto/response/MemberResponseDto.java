package SpringSecurityOauth2.domain.member.service.dto.response;

import SpringSecurityOauth2.domain.member.entity.Member;
import SpringSecurityOauth2.domain.member.entity.Role;
import lombok.Builder;

@Builder
public record MemberResponseDto(
        String uuid,
        String name,
        Role role,
        String email,
        String memberImage
) {
    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .uuid(member.getUuid())
                .name(member.getName())
                .role(member.getRole())
                .email(member.getEmail())
                .memberImage(member.getMemberImage())
                .build();
    }
}
