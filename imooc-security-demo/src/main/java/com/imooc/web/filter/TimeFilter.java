package com.imooc.web.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
// @Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("{}", "过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("{}", "过滤器执行开始");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("{}", "过滤器执行结束");
    }

    @Override
    public void destroy() {
        log.info("{}", "过滤器销毁");
    }
}

