package com.example.demo.daoPackage;

import com.example.demo.pocoPackage.Customer;
import com.example.demo.pocoPackage.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightsDAO implements DAO{
    public List<Flight>flightList=new ArrayList<>();
    //return flight by id
    @Override
    public Flight get(long id) {
        Flight flight=null;
        try {
            var result=stm.executeQuery("select * from Flights where id="+id);
            result.next();
            flight=getFlight(result);
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry can't get the flight");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return flight;
    }
    //return list of all the flights
    @Override
    public List getAll() {
        try {
            var result =stm.executeQuery("select * from Flights");
            while(result.next()){
                flightList.add(getFlight(result));
            }
            result.close();
        } catch (SQLException e) {
            System.out.println("something went wrong ");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return flightList;
    }
    //add a flight
    @Override
    public boolean add(Object o) {
        Flight flight= (Flight) o;
        try {
            stm.executeUpdate("insert into Flights (airline_company_id,origin_country_id,destination_country_id,depature_time,landing_time,remaining_tickets) " +
                    "values ("+flight.airlineCompany+","+flight.originCountryId+","+flight.destinationCountryId+",\'"+flight.departureTime+"\',\'"+flight.landingTime+"\',"+flight.remainingTickets+")");
        } catch (SQLException e) {
            System.out.println("sorry,you can't add the flight ");
            return false;
        }
        return true;
    }
    //remove flight
    @Override
    public boolean remove(Object o) {
        Flight flight= (Flight) o;
        try {
            stm.executeUpdate("DELETE from Flights WHERE id = "+flight.id);
        } catch (SQLException e) {
            System.out.println("sorry,you can't remove the flight ");
            return false;
        }
        return true;
    }
    //update a flight
    @Override
    public boolean update(Object o) {
        Flight flight= (Flight) o;
        try {
            stm.executeUpdate("UPDATE Flights SET id= "+flight.id+",airline_company_id="+ flight.airlineCompany+",origin_country_id=" +flight.originCountryId
                    +",destination_country_id="+flight.destinationCountryId+",depature_time=\'"+flight.departureTime+"\',landing_time=\'"+flight.landingTime
                    +"\',remaining_tickets="+flight.remainingTickets+
                    " where id="+flight.id);
        } catch (SQLException e) {
            System.out.println("sorry, can't update this customer");
            return false;
        }
        return true;
    }
    //return flight by the origin country id
    public List getFlightsByOriginCountryId(int country_id){
        List<Flight> flightList=new ArrayList<>();
        try {
            var result=stm.executeQuery("select * from Flights where origin_country_id="+country_id);
            while(result.next()) {
                flightList.add(getFlight(result));
            }
        } catch (SQLException e) {
            System.out.println("sorry can't get the flight by origin country id ");
        }
        return flightList;
    }
    //return flight by destination country id
    public List getFlightsByDestinationCountryId(int country_id) {
        List<Flight> flightList=new ArrayList<>();
        try {
            var result=stm.executeQuery("select * from Flights where destination_country_id="+country_id);
            while(result.next()) {
                flightList.add(getFlight(result));
            }
        } catch (SQLException e) {
            System.out.println("sorry can't get the flight");
        }
        return flightList;
    }
    // return flight by depature time
    public List getFlightsByDepartureDate(String Sdate) {
        List<Flight> flightList=new ArrayList<>();
        Timestamp date=null;
        try {
            date = Timestamp.valueOf(Sdate);
        }catch (Exception e){
            System.out.println("the date must be in timestamp format");
        }
        try {
            var result=stm.executeQuery("select * from Flights where depature_time=\'"+date+"\'");
            while(result.next()) {
                flightList.add(getFlight(result));
            }
        } catch (SQLException e) {
            System.out.println("sorry can't get the flight pay attention to  the format");
        }
        return flightList;
    }
    //return flight by landing time
    public List getFlightsByLandingDate(String Sdate){
        List<Flight> flightList=new ArrayList<>();
        Timestamp date=null;
        try {
            date = Timestamp.valueOf(Sdate);
        }catch (Exception e){
            System.out.println("the date must be in timestamp format");
        }
        try {
            var result=stm.executeQuery("select * from Flights where landing_time=\'"+date+"\'");
            result.next();
            flightList.add(getFlight(result));
        } catch (SQLException e) {
            System.out.println("sorry can't get the flight");
        }
        return flightList;
    }
    //return all the flights that some customer have a ticket in from all the flights
    public List getFlightsByCustomer( int customer){
        List<Flight> flight=new ArrayList<>();
        try {
            var result=stm.executeQuery("select * from get_flight_by_customer(\'"+customer+"\')");
            while(result.next()) {
                flight.add(getFlight(result));
            }
        } catch (SQLException e) {
            System.out.println("sorry can't get the flight that this customer bought");
        }
        return flight;
    }
    //return all the flights that has this values in those parameters
    public List getFlightByParameters(int originCountryId, int destinationCountryId,String Sdate){
        List<Flight> flight=new ArrayList<>();
        LocalDate date=null;
        //Timestamp date=null;
        try {
            date= LocalDate.parse(Sdate);
            //date = Timestamp.valueOf(Sdate);
        }catch (Exception e){
            System.out.println("the date must be in date format");
        }
        try {
            var result=stm.executeQuery("select * from get_flights_by_parameters("+originCountryId+","+ destinationCountryId+",\'"+
                    date+"\')");
            while(result.next()) {
                flight.add(getFlight(result));
            }
        } catch (SQLException e) {
            System.out.println("sorry can't get the flight by this parameters ");
        }
        return flight;
    }
    //return all the flights of specific airline company by the id
    public List getFlightsByAirlineId(long airlineId){
        List<Flight> flight=new ArrayList<>();
        try {
            var result=stm.executeQuery("select * from get_flights_by_airline_id("+airlineId+")");
            while(result.next()) {
                flight.add(getFlight(result));
            }
        } catch (SQLException e) {
            System.out.println("sorry can't get the flight by airline id ");
        }
        return flight;
    }
    //return all the flights that arrive in the next 12 hours
    public List getArrivalFlights(int countryId){
        List<Flight> flight=new ArrayList<>();
        try {
            var result=stm.executeQuery("select * from get_arrival_flights("+countryId+")");
            while(result.next()) {
                flight.add(getFlight(result));
            }
        } catch (SQLException e) {
            System.out.println("sorry can't get the arriving in 12 hours flights");
        }
        return flight;
    }
    //return all the flights that denature in the next 12 hours
    public List getDepartureFlights(int countryId){
        List<Flight> flight=new ArrayList<>();
        try {
            var result=stm.executeQuery("select * from get_departure_flights("+countryId+")");
            while(result.next()) {
                flight.add(getFlight(result));
            }
        } catch (SQLException e) {
            System.out.println("sorry can't get the denaturing in 12 hours flights");
        }
        return flight;
    }
    //get flights by result set (help function )
    private Flight getFlight(ResultSet result){
        Flight flight=null;
        try {
            flight=new Flight(result.getLong("id"),
                    result.getLong("airline_company_id"),
                    result.getInt("origin_country_id"),
                    result.getInt("destination_country_id"),
                    result.getString("depature_time"),
                    result.getString("landing_time"),
                    result.getInt("remaining_tickets"));
        } catch (SQLException e) {
            System.out.println("flight was not found in the db");
        }
        return flight;
    }
    //update remaining tickets plus 1
    public void updateRemainingTicketsPlus(long flightId){
        Flight flight=this.get(flightId);
        flight.remainingTickets++;
        this.update(flight);
    }
    //update remaining tickets minus 1
    public void updateRemainingTicketsMinus(long flightId){
        Flight flight=this.get(flightId);
        flight.remainingTickets--;
        this.update(flight);
    }
}
