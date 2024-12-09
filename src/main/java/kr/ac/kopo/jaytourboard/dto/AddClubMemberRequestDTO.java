package kr.ac.kopo.jaytourboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddClubMemberRequestDTO {
    private String email;
    private String password;
}
