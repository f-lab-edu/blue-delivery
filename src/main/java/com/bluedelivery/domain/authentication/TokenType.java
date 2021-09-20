package com.bluedelivery.domain.authentication;

import static java.util.Objects.nonNull;

public enum TokenType {
    BASIC("Basic"),
    BEARER("Bearer"),
    DIGEST("Digest");
    
    private String type;
    
    TokenType(String type) {
        this.type = type;
    }
    
    public String extract(String authenticationHeader) {
        if (isValid(authenticationHeader)) {
            return authenticationHeader.substring(type.length());
        }
        return "";
    }
    
    public String getType() {
        return type;
    }
    
    private boolean isValid(String authorization) {
        return nonNull(authorization) && authorization.startsWith(type);
    }
}
