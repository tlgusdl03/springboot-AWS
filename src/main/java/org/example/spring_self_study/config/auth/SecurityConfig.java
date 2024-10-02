package org.example.spring_self_study.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.spring_self_study.domain.user.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration  // Configuration 클래스로 설정
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화
                .csrf(csrf -> csrf.disable())
                // Frame 옵션 비활성화 (H2 콘솔 사용을 위해)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

                // 요청에 대한 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())  // 사용자 권한 확인
                        .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )

                // OAuth2 로그인 설정
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                );

        return http.build();
    }

}

