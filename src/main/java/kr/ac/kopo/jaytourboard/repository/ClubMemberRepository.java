package kr.ac.kopo.jaytourboard.repository;

import kr.ac.kopo.jaytourboard.entity.ClubMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
    Optional<ClubMember> findByEmail(String email); // 이메일로 사용자 정보를 가져옴
}