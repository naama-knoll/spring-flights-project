package com.example.demo.BusniessLogics;

import com.example.demo.daoPackage.AirlineCompaniesDAO;
import com.example.demo.daoPackage.CountriesDAO;
import com.example.demo.daoPackage.FlightsDAO;
import com.example.demo.daoPackage.UsersDAO;
import com.example.demo.pocoPackage.AirlineCompany;
import com.example.demo.pocoPackage.Country;
import com.example.demo.pocoPackage.Flight;
import com.example.demo.pocoPackage.User;

import java.util.Date;
import java.util.List;

public abstract class FacadeBase {
    //return list of all the flights
    public List getAllFlights(){
        FlightsDAO flightsDAO=new FlightsDAO();
        return flightsDAO.getAll();
    }
    //return flight by id
    public Flight getFlightById(long id){
        FlightsDAO flightsDAO=new FlightsDAO();
        return flightsDAO.get(id);
    }
    //return all the flights that has this values in those parameters
    public List getFlightsByParameters(int origin_country_id, int destination_country_id, String date){
        FlightsDAO flightsDAO=new FlightsDAO();
        return flightsDAO.getFlightByParameters(origin_country_id,destination_country_id,date);
    }
    //return all the airline companies
    public List getAllAirlines(){
        AirlineCompaniesDAO airlineCompany=new AirlineCompaniesDAO();
        return airlineCompany.getAll();
    }
    //return airline company by id
    public AirlineCompany getAirlineById(long id){
        AirlineCompaniesDAO airlineCompany=new AirlineCompaniesDAO();
        return airlineCompany.get(id);
    }
    //get airline company by parameter name
    public AirlineCompany getAirlineByParameters(String airlineName){
        AirlineCompaniesDAO airlineCompany=new AirlineCompaniesDAO();
        return airlineCompany.getAirlineByName(airlineName);
    }
    //get all countries
    public List getAllCountries(){
        CountriesDAO countriesDAO=new CountriesDAO();
        return countriesDAO.getAll();
    }
    //get country by id
    public Country getCountryById(long id){
        CountriesDAO countriesDAO=new CountriesDAO();
        return countriesDAO.get(id);
    }
    // create new user
    protected boolean createNewUser ( User user ){
        if(checkUser(user)){
            UsersDAO usersDAO=new UsersDAO();
            usersDAO.add(user);
            return true;
        }
        return false;
    }
    //check user correctness
    protected boolean checkUser(User user){
        try {
            if (user.password.length() < 6)
                return false;
        }catch (Exception e){
            System.out.println("opps , no password given so you cant create this user");
        }
        return true;
    }

    public static void printError(String action,String user){
        System.out.println("sorry "+action + " this "+user+" failed");
    }
}
