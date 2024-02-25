package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("kim");

        memberService.join(member);

        assertEquals(member, memberRepository.find(member.getId()));
    }

    @Test
    void 회원_중복_가입() {
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }
        fail("예외가 발생해야 합니다.");
    }

}