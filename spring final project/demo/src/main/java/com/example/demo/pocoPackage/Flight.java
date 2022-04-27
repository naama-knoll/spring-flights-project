package com.example.demo.pocoPackage;

import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
@EqualsAndHashCode
public class Flight implements POCO{
    public long id;
    public long airlineCompany;
    public int originCountryId;
    public int destinationCountryId;
    public Timestamp departureTime;
    public Timestamp landingTime;
    public int remainingTickets;

    public Flight( long id,long airlineCompany, int originCountryId, int destinationCountryId, String departureTime, String landingTime, int remainingTickets) {
        this.id=id;
        this.airlineCompany = airlineCompany;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        try {
            this.departureTime = Timestamp.valueOf(departureTime);
            this.landingTime = Timestamp.valueOf(landingTime);
        }catch (IllegalArgumentException e){
            System.out.println("you must enter times in correct format");
            this.departureTime=Timestamp.valueOf("1900-01-01 00:00:00");
            this.landingTime=Timestamp.valueOf("1900-01-01 00:00:00");
        }
        this.remainingTickets = remainingTickets;
    }

    public Flight() {
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineCompany=" + airlineCompany +
                ", originCountryId=" + originCountryId +
                ", destinationCountryId=" + destinationCountryId +
                ", departureTime=" + departureTime +
                ", landingTime=" + landingTime +
                ", remainingTickets=" + remainingTickets +
                '}';
    }
}
