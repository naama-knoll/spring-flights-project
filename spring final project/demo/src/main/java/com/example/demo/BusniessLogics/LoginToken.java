package com.example.demo.BusniessLogics;

public class LoginToken {
    public long id;
    public String name;
    public UserRole role;

    public LoginToken(long id, String name, UserRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public LoginToken() {
    }

    @Override
    public String toString() {
        return "LoginToken{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
