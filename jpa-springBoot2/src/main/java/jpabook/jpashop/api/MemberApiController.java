package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
                .build();
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members")
    public EditMemberResponse editMemberV2(@RequestBody @Valid EditMemberRequest request) {
        Member member = Member.builder()
                .name(request.name)
                .address(new Address(request.city, request.street, request.zipCode))
                .build();
        memberService.update(member.getId(), member.getName());
        return new EditMemberResponse(member.getId());
    }

    @GetMapping("api/v2/members")
    public Result getMembersV2 () {
        List<Member> members = memberService.findMembers();
        List<GetMemberResponse> memberResponses = members.stream().map(member -> new GetMemberResponse(member.getId(), member.getName())).toList();
        return new Result(memberResponses.size(),memberResponses);
    }

@Data
@AllArgsConstructor
static class Result<T> {
        private int size;
        private T data;
}

@Data
@AllArgsConstructor
static class GetMemberResponse {
    Long id;
    String name;
}

    @Data
    @AllArgsConstructor
    static class EditMemberResponse {
        private long id;
    }

    @Data
    static class EditMemberRequest {
        private String name;
        private String city;
        private String street;
        private String zipCode;
    }

    @Data
    static class CreateMemberResponse {
        private long id;
        private String name;

        public CreateMemberResponse(long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }
}
