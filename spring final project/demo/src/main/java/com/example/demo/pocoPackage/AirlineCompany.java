package com.example.demo.pocoPackage;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class AirlineCompany implements POCO{
    public long id;
    public String name;
    public int countryId;
    public long userId;

    public AirlineCompany(long id,String name, int countryId, long userId) {
        this.id=id;
        this.name = name;
        this.countryId = countryId;
        this.userId = userId;
    }

    public AirlineCompany() {
    }

    @Override
    public String toString() {
        return "AirlineCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryId=" + countryId +
                ", userId=" + userId +
                '}';
    }
}
