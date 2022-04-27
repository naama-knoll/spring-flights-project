package com.example.demo.BusniessLogics;

import com.example.demo.daoPackage.AirlineCompaniesDAO;
import com.example.demo.daoPackage.FlightsDAO;
import com.example.demo.pocoPackage.AirlineCompany;
import com.example.demo.pocoPackage.Flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class AirlineFacade extends AnonymousFacade {
    public AirlineCompany airlineCompany;
    public LoginToken token;
    public FlightsDAO flightsDAO = new FlightsDAO();
    public AirlineCompaniesDAO airlineCompaniesDAO = new AirlineCompaniesDAO();


    public AirlineFacade(LoginToken token) {
        this.token = token;
        this.airlineCompany = airlineCompaniesDAO.getAirlineByUsername(token.name);
    }

    public AirlineFacade() {
        this.token=new LoginToken(1,"swiss",UserRole.AIRLINE_COMPANY);
    }

    //returns all the flights of this airline company
    public List getMyFlights() {
        if (token.role == UserRole.AIRLINE_COMPANY)
            return flightsDAO.getFlightsByAirlineId(this.token.id);
        return new ArrayList<>();
    }

    //update airline company data
    public boolean updateAirline(AirlineCompany airlineCompany) {
        if (this.airlineCompany.id == airlineCompany.id && this.token.id == airlineCompany.userId && this.token.role == UserRole.AIRLINE_COMPANY) {
            airlineCompaniesDAO.update(airlineCompany);
            return true;
        }
        printError("updating","airline company");
        return false;
    }

    //add flight
    public boolean addFlight(Flight flight) {
        if (checkFlightValues(flight) && this.token.role == UserRole.AIRLINE_COMPANY) {
            flightsDAO.add(flight);
            return true;
        }
        printError("adding","flight");
        return false;
    }

    //update flight
    public boolean updateFlight(Flight flight) {
        if (checkFlightValues(flight) && this.token.role == UserRole.AIRLINE_COMPANY) {
            flightsDAO.update(flight);
            return true;
        }
        printError("updating","flight");
        return false;
    }

    private boolean checkFlightValues(Flight flight) {
        long depature = flight.departureTime.getTime();
        long land = flight.landingTime.getTime();
        long now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        try {
            if (flight.remainingTickets <= 0) {
                throw new Exception("you cannot create a flight that has no more tickets available");
            }
            if (depature >= land || depature < now || land < now) {
                throw new Exception("incorrect flight times");
            }
            if (flight.originCountryId == flight.destinationCountryId) {
                throw new Exception("cannot create flight than origin country and destination country are same");
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    //remove flight
    public boolean removeFlight(Flight flight) {
        if (this.token.role == UserRole.AIRLINE_COMPANY) {
            flightsDAO.remove(flight);
            return true;
        }
        printError("removing","flight");
        return false;
    }
}
