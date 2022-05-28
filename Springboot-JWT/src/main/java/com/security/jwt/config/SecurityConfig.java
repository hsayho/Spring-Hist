package com.security.jwt.config;

import com.security.jwt.config.jwt.JwtAuthenticationFilter;
import com.security.jwt.config.jwt.JwtAuthorizationFilter;
import com.security.jwt.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); // 지정 Filter가 맨 처음에 동작하게 됨(SecurityContextPersistencceFilter가 가장 높은 우선순위를 가지므로)
        http.csrf().disable();
        // 세션을 사용하지 않겠다는 의미
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// Bearer 방식을 위한 세팅1
                .and()
                .addFilter(corsFilter) // @CrossOrigin으로도 처리할 수 있으나 인증을 사용할 때는 시큐리티 필터에 등록을 해야 함
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // 파라미터로 AuthenticationManager를 꼭 던져줘야 함
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .formLogin().disable()
                .httpBasic().disable() // Bearer 방식을 위한 세팅1
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
