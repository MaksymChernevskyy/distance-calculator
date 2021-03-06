package com.imoovo.business.entity;

import javax.validation.constraints.Size;

public class User {
    private Long id;
    @Size(max = 30, message = "Name can be have max 30 symbols")
    private String name;
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
