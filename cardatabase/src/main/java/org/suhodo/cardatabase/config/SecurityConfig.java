package org.suhodo.cardatabase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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

    // LoginController에서 사용할 AuthenticationManager객체를 Bean으로 등록
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    // 스프링 시큐리티에서 어떤 경로는 보호/비보호 결정
    // 보안 적용/비적용 결정
    /* CSRF는 Session을 사용하는데, 우리는 Ajax json STATELESS 통신이므로, Session 관리가 없다.
       불필요해서 disable */
    // login 주소 요청은 허용한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        log.info("filterChain................");

        http.csrf((csrf)->csrf.disable())
            .sessionManagement((sessionManagement)->sessionManagement.
                    sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests
                    .requestMatchers(HttpMethod.POST, "/login").permitAll().
                    anyRequest().authenticated());

        return http.build();
    }
}