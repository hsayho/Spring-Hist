package com.security.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        /**
         * 토큰 : cos는 토큰의 역할을 하는데 우리가 이것을 만들어 줘야 함.
         * id, pwd가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 그걸 응답을 해줌.
         * 요청할 때마다 header에 Authorization에 value 값으로 토큰을 가지고 올 것이고,
         * 그 때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증 하면 됨 (RSA, HS256)
         * */
        if(req.getMethod().equals("POST")) {
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);

            if(headerAuth.equals("cos")) {
                chain.doFilter(req, res); // 필터 이후에 프로세스를 진행시키기 위한 코드
            }else {
                PrintWriter out = res.getWriter();
                out.println("인증안됨");
            }
        }
    }
}
