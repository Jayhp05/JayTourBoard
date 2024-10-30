package kr.ac.kopo.jaytourboard.repository;

import kr.ac.kopo.jaytourboard.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1, 10).forEach(i ->{
            Member member = Member.builder()
                    .email("user"+i+"@gmail.com")
                    .password("1234")
                    .name("user"+i)
                    .build();
            memberRepository.save(member);
        });
    }
}