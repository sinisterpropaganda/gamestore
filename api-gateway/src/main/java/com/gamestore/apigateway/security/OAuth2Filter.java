/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.apigateway.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author qbuser
 */
public class OAuth2Filter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getServletPath();
        System.out.println("servlet_path: " + path);
        if (path.contains("/user-service/users/")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(auth)) {
                OAuth2AuthenticationDetails oauthDetails
                        = (OAuth2AuthenticationDetails) auth.getDetails();
                System.out.println("oauth_authe" + oauthDetails == null);
                System.out.println("decoded: " + oauthDetails
                        .getDecodedDetails());
//                User user = (User) auth.getPrincipal();
                Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
                authorities.forEach(autho -> System.out.println("authority: " + autho.getAuthority()));
                System.out.println("username: " + auth.getPrincipal());
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public FilterConfig getFilterConfig() {
        return super.getFilterConfig(); //To change body of generated methods, choose Tools | Templates.
    }

}
