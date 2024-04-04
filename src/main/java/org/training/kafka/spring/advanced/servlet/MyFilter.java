package org.training.kafka.spring.advanced.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;

import java.io.IOException;

@WebFilter("/api/*")
public class MyFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest servletRequestParam,
                         final ServletResponse servletResponseParam,
                         final FilterChain filterChainParam) throws IOException, ServletException {
        String nameLoc = servletRequestParam.getParameter("name");
        System.out.println("MyFilter : " + nameLoc);
        if (nameLoc == null || nameLoc.equals("osman")) {
            HttpServletResponse responseLoc = (HttpServletResponse) servletResponseParam;
            responseLoc.addHeader("Content-Type", "text/plain;charset=UTF-8");
            responseLoc.getWriter().println("Osman yada boş bir name geçemez");
            return;
        }
        filterChainParam.doFilter(servletRequestParam,
                                  servletResponseParam);
    }
}
