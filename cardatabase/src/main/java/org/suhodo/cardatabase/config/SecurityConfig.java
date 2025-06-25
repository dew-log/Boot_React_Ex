package org.suhodo.cardatabase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.suhodo.cardatabase.service.UserDetailsServiceImpl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.log4j.Log4j2;

/*
 * 스프링 시큐리티 설정
 * 아래 어노테이션을 설정하면 기본 웹 보호 구성을 해제
 * -> 우리가 직접 설정한다.
 */
@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
   
    /* 아래 코드는 직접 인증에 필요한 AuthenticationManagerBuilder를 설정하는 역할이지만
     * 현재는 직접 등록하지 않아도 됨.
     */
    // 시큐리티 인증과정에 필요한 AuthenticationManagerBuilder 객체에
    // DB의 사용자 정보를 반환하는 역할을 하는 userDetailsService를 등록
    // 패스워드 암호화 시 BCryptPasswordEncoder를 사용하겠다.
    /*
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManagerBuilder auth;

    // 생성자 호출 이후에 자동 실행(초기화)
    @PostConstruct
    public void configureGlobal() throws Exception {
        log.info("configureGlobal................");

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    */

    // BCrypt 해싱 알고리즘 객체(password -> hashcode로 변환)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}