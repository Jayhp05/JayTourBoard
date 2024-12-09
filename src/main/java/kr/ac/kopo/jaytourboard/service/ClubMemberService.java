package kr.ac.kopo.jaytourboard.service;

import kr.ac.kopo.jaytourboard.dto.AddClubMemberRequestDTO;
import kr.ac.kopo.jaytourboard.entity.Board;
import kr.ac.kopo.jaytourboard.entity.ClubMember;
import kr.ac.kopo.jaytourboard.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddClubMemberRequestDTO dto){
        return clubMemberRepository.save(ClubMember.builder()
                .email(dto.getEmail())
                // 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
