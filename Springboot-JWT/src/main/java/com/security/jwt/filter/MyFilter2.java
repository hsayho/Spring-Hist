package com.security.jwt.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter2 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter 2");
        chain.doFilter(request, response); // 필터 이후에 프로세스를 진행시키기 위한 코드
    }
}
