package com.bazzi.cherryfeed.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //스프링부트가 버전이 올라가면서 기존에 쓰던 방법대신 빈으로 SecurityFilterChain을 제정의해서 사용한다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable() //UI쪽으로 들어오는걸 disable한다.
                .csrf().disable()      //크로스사이트기능
                .cors().and()          //크로스사이트에서 도메인이다를때 허용한다.
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/v1/users/join" , "/api/v1/users/login").permitAll() //이곳을 허용한다.
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt를 사용하는경우 씀
                .and()
                .build();
    }
}
