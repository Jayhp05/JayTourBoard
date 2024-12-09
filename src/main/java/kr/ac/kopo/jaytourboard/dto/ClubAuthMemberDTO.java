package kr.ac.kopo.jaytourboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User {

    private String email;
    private String name;
    private boolean fromsocial;

    public ClubAuthMemberDTO(String username, String password, boolean fromsocial, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromsocial = fromsocial;
    }
}