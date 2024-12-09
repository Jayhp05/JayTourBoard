package kr.ac.kopo.jaytourboard.config;

import kr.ac.kopo.jaytourboard.service.ClubUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
@Log4j2
public class SecurityConfig {
    private final ClubUserDetailsService clubUserDetailsService;

    // 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests() // 인증, 인가 설정
                .requestMatchers("/Login", "/SignUp").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() // 폼 기반 로그인 설정
                .loginPage("/Login")
                .defaultSuccessUrl("/Web_report")
                .and()
                .logout() // 로그아웃 설정
                .logoutSuccessUrl("/Login")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable() // csrf 비활성화 -> 실습을 위해 잠깐 비활성화!!
                .build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, ClubUserDetailsService clubUserDetailsService)
            throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(clubUserDetailsService) // 사용자 정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}