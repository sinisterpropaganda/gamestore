/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.apigateway.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author qbuser
 */
@Component
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";
    private static final Logger LOGGER
            = LoggerFactory.getLogger(OAuth2FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && (securityContext.getAuthentication().getDetails() instanceof OAuth2AuthenticationDetails)) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) securityContext.getAuthentication().getDetails();

            if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
                LOGGER.info("Authorization header is already set");
            } else {
                template.header(AUTHORIZATION_HEADER,
                        String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
            }
        }
    }

}
