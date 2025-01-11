package SpringSecurityOauth2.domain.member.controller;

import SpringSecurityOauth2.domain.member.service.MemberService;
import SpringSecurityOauth2.domain.member.service.dto.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity<MemberResponseDto> getMyInfo() {
        MemberResponseDto responseDto = memberService.getMyInfo();
        return ResponseEntity.ok(responseDto);
    }

}
