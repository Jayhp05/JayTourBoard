package kr.ac.kopo.jaytourboard.service;

import kr.ac.kopo.jaytourboard.entity.ClubMember;
import kr.ac.kopo.jaytourboard.repository.ClubMemberRepository;
import kr.ac.kopo.jaytourboard.dto.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;

    // 사용자 이름(email)으로 사용자 정보를 가져오는 메소드
    @Override
    public ClubMember loadUserByUsername(String email) {
        return clubMemberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}