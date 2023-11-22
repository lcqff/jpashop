package jpabook.jpashop.repository;

import static org.junit.jupiter.api.Assertions.*;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember () {
        Member member = new Member();
        member.setName("memberA");
        
        Long saveid = memberRepository.save(member);
        Member findMember = memberRepository.find(saveid);
        memberRepository.find(saveid);

        assertEquals(findMember.getId(), member.getId());
        assertEquals(findMember.getName(), member.getName());

    }

    @Test
    void find() {
    }
}