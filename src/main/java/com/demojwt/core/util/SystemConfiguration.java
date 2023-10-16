package com.demojwt.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfiguration {

    @Value("${secret.key}")
    private String secretKey;
//    @Value("${secret.key.for.no.expiry}")
    private String noExpiryKey;
//    @Value("${token.prefix}")
    private String tokenPrefix;
//    @Value("${header.string}")
    private String headerString;
//    @Value("${sign.up.url}")
    private String signUpUrl;
//    @Value("${admin.gard}")
    private String adminGard;

//    @Value("${old.password.max.count}")
    private int oldPasswordMaxCount;

//    @Value("${allow.multiple.login}")
    private boolean allowMultipleLogin;

    public String getSecretKey() {
        return secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public String getHeaderString() {
        return headerString;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public int getOldPasswordMaxCount() {
        return oldPasswordMaxCount;
    }

    public String getAdminGard() {
        return adminGard;
    }

    public String getNoExpiryKey() {
        return noExpiryKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setNoExpiryKey(String noExpiryKey) {
        this.noExpiryKey = noExpiryKey;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public void setHeaderString(String headerString) {
        this.headerString = headerString;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public void setAdminGard(String adminGard) {
        this.adminGard = adminGard;
    }

    public void setOldPasswordMaxCount(int oldPasswordMaxCount) {
        this.oldPasswordMaxCount = oldPasswordMaxCount;
    }

    public boolean isAllowMultipleLogin() {
        return allowMultipleLogin;
    }

    @Override
    public String toString() {
        return "SystemConfiguration{" +
                "secretKey='" + secretKey + '\'' +
                ", noExpiryKey='" + noExpiryKey + '\'' +
                ", tokenPrefix='" + tokenPrefix + '\'' +
                ", headerString='" + headerString + '\'' +
                ", signUpUrl='" + signUpUrl + '\'' +
                ", adminGard='" + adminGard + '\'' +
                ", oldPasswordMaxCount=" + oldPasswordMaxCount +
                '}';
    }
}
