package com.example.demo.pocoPackage;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Administrator implements POCO{
    public long id;
    public String firstName;
    public String lastName;
    public long userId;

    public Administrator( long id ,String firstName, String lastName, long userId) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    public Administrator() {}

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
