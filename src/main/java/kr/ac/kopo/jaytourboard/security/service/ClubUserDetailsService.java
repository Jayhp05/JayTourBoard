package kr.ac.kopo.jaytourboard.security.service;

import kr.ac.kopo.jaytourboard.entity.ClubMember;
import kr.ac.kopo.jaytourboard.repository.ClubMemberRepository;
import kr.ac.kopo.jaytourboard.security.dto.ClubAuthMemberDTO;
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
public class ClubUserDetailsService implements UserDetailsService {
    private final ClubMemberRepository clubMemberRepository;

    public ClubUserDetailsService(ClubMemberRepository clubMemberRepository) {
        this.clubMemberRepository = clubMemberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("★ ClubUesrDetailsService" + username);
        Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);

        if (result.isEmpty()){
            throw new UsernameNotFoundException("Check Email or social");
        }

        ClubMember clubMember = result.get();

        log.info(clubMember);

        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet())
        );

        clubAuthMemberDTO.setName(clubMember.getName());
//        clubAuthMemberDTO.setFromsocial(clubMember.isFromSocial);

        return clubAuthMemberDTO;
    }
}