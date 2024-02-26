package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateMember(member);
        memberRepository.save(member);
        return  member.getId();
    }

    private void validateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (findMembers.isEmpty()) {
            return;
        }
        throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    // 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        return memberRepository.find(id);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.find(id);
        member.setName(name);
        memberRepository.save(member);
    }

    public Member getMember(Long id) {
        Member member = memberRepository.find(id);
        return member;
    }
}
