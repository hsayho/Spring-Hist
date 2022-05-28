package com.security.jwt.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.jwt.config.auth.PrincipleDetails;
import com.security.jwt.model.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/**
 * Spring Security에서 UsernamePasswordAuthenticationFilter가 있음.
 * 이 필터는 /login 요청해서 username, password 전송하면(POST)
 * UsernamePasswordAuthenticationFilter가 동작함
 *
 * 우린 formLogin을 disable했기 때문에 이것을 구현하여 security config에 추가해주면 된다.
 * */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // login 요청을 하면 login 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");

         // PrincipalDetails를 세션에 담고 (권한 관리를 위해서)
         // JWT 토큰을 만들어서 응답해주면 됨

        try {
            ObjectMapper om = new ObjectMapper();
            // username, password를 받아서
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            // 정상인지 로그인 시도 -> authenticationManager로 로그인 시도를 하면
            // PrincipalDetailsService가 호출 -> loadUserByUsername() 함수 실행
            // 정상적으로 로그인이 되면 authentication이 반환됨
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

            // authentication 객체가 session 영역에 저장됨. -> 로그인이 되었다는 뜻
            PrincipleDetails principleDetails = (PrincipleDetails) authentication.getPrincipal();
            System.out.println(principleDetails.getUser().getUsername());

            // authentication 객체를 반환하는 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는 것
            // 굳이 JWT 토큰을 이용하면서 세션을 만들 이유가 없음 -> but, 단지 권한 처리 때문에 session을 넣어 줌
            return authentication;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * attemptAuthentication 실행 후 인증이 정상적으로 되면 실행
     * JWT 토큰을 만들어서 request 요청한 사용자에게 토큰을 response 해주면 됨
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication : 인증 성공");
        PrincipleDetails principalDetails = (PrincipleDetails) authResult.getPrincipal();

        Key key = Keys.hmacShaKeyFor("MyNickNameisErjuerAndNameisMinsu".getBytes(StandardCharsets.UTF_8));

        String jwtToken = Jwts.builder()
                .setSubject("cos토큰")
                .setExpiration(new Date(System.currentTimeMillis() + 60000 * 10))
                .claim("username", principalDetails.getUser().getId())
                .claim("username", principalDetails.getUser().getUsername())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        response.addHeader("Authorization", "Bearer"+jwtToken);
    }
}
