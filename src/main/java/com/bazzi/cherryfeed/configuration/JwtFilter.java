package com.bazzi.cherryfeed.configuration;

import com.bazzi.cherryfeed.apps.account.service.UserService;
import com.bazzi.cherryfeed.utils.JwtTokenUtil;
//import com.google.common.net.HttpHeaders;
import org.springframework.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final String key;

    //jwt필터 문이라고 생각하면됨. 여기를 통하면 접근할 권한을 부여할수있다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);


        if (
                path.startsWith("/api/v1/users/join")
                        || path.startsWith("/api/v1/users/login")
                        || path.startsWith("/api/v1/users/duplicationcheck/nickname")
                        || path.startsWith("/api/v1/users/create/connectcode")
                        || path.startsWith("/swagger-ui.html")
                        || path.startsWith("/kakao")
                        || path.startsWith("/kakaoToken")
        ) {
            // "/api/v1/users"와 "/api/v1/posts" 경로를 제외한 API는 필터링하지 않습니다.
            filterChain.doFilter(request, response);
            return;
        }
        // token안보내면 Block
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization을 잘못 보냈습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        //토큰 꺼내기
        String token = authorization.split(" ")[1];

        // 토큰 Expired되었는지 여부
        if (JwtTokenUtil.isExpired(token, key)) {
            log.error("토큰이 만료 되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // UserName Token에서 꺼내기
        String email = JwtTokenUtil.getEmail(token, key);
        String userName = JwtTokenUtil.getUserName(token, key);
        Long id = JwtTokenUtil.getId(token, key);
        log.info("email:{}", email);
        log.info("userName:{}", userName);


        // 권한 부여
        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(id, null, Arrays.asList(new SimpleGrantedAuthority("USER"))); //DB에 role같은걸 지정해 놓았으면 거기에서 꺼내서 넣을 수있다.
        //20230902위 소스로 수정
        /*UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(id, null, List.of(new SimpleGrantedAuthority("USER"))); //DB에 role같은걸 지정해 놓았으면 거기에서 꺼내서 넣을 수있다.
        
        // Detail
         */
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
