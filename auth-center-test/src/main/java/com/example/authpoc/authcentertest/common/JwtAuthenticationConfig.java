package com.example.authpoc.authcentertest.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${emoket.security.jwt.url:/login}")
    private String url;

    @Value("${emoket.security.jwt.header:Authorization}")
    private String header;

    @Value("${emoket.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${emoket.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${emoket.security.jwt.secret}")
    private String secret;

    public String getUrl() {
        return url;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSecret() {
        return secret;
    }

    public long getExpiration() {
        return expiration;
    }
}
