package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

@PostMapping("/api/v2/members")
public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
    Member member = Member.builder()
            .name(request.name)
            .address(new Address(request.city,request.street,request.zipCode))
            .build();
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
}

    @Data
    static class CreateMemberResponse {
        private long id;
        private String name;
        private String city;
        private String street;
        private String zipCode;

        public CreateMemberResponse(long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
        private String city;
        private String street;
        private String zipCode;

        public CreateMemberRequest(long id) {
            this.id = id;
        }
    }
}
