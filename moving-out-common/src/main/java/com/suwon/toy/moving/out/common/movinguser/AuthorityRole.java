package com.suwon.toy.moving.out.common.movinguser;

public enum AuthorityRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    AuthorityRole(String value) { this.value = value; }

    public String getValue() { return value; }
}
