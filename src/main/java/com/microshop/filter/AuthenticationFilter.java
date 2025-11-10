package com.microshop.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/profile/*", "/payment/*"})
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        Object user = session.getAttribute("user");

        if (user == null) {
            if (session.getAttribute("redirectAfterLogin") == null) {
                String redirectAfterLogin = req.getRequestURI();
                if (req.getQueryString() != null) {
                    redirectAfterLogin += "?" + req.getQueryString();
                }
                session.setAttribute("redirectAfterLogin", redirectAfterLogin);
            }
            res.sendRedirect(req.getContextPath() + "/login?msg=login_required");
            return;
        }
        chain.doFilter(request, response);
    }
}
