package com.example.demo.daoPackage;

import com.example.demo.pocoPackage.Administrator;
import com.example.demo.pocoPackage.AirlineCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompaniesDAO implements DAO{
    public List<AirlineCompany> airlineCompanyList=new ArrayList<>();
    //return airline company by id
    @Override
    public AirlineCompany get(long id) {
        AirlineCompany airlineCompany=null;
        try {
            var result = stm.executeQuery("select * from Airline_Companies where id="+id);
            result.next();
            airlineCompany=getAirlineCompany(result);
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry, can't get this airline company");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return airlineCompany;
    }
    //return list of all the airline companies exists
    @Override
    public List getAll() {
        try {
            var result=stm.executeQuery("select * from Airline_Companies");
            while(result.next()){
                airlineCompanyList.add(getAirlineCompany(result));
            }
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry , some problem occurred");
        } catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return airlineCompanyList;    }
    //add new airline company
    @Override
    public boolean add(Object o) {
        AirlineCompany arc= (AirlineCompany) o;
        try {
            stm.executeUpdate("insert into Airline_Companies (name,country_id,user_id) values (\'"+arc.name+"\',"+arc.countryId+","+arc.userId+")");
        } catch (SQLException e) {
            System.out.println("sorry,you can't add the airline company ");
            return false;
        }
        return true;
    }
    //remove airline company
    @Override
    public boolean remove(Object o) {
        AirlineCompany arc= (AirlineCompany) o;
        try {
            stm.executeUpdate("DELETE from Airline_Companies WHERE id = "+arc.id);
        }
        catch (SQLException e){
            System.out.println("sorry, you can't remove this airline company");
            return false;
        }
        return true;
    }
    //update a airline company
    @Override
    public boolean update(Object o) {
        AirlineCompany arc= (AirlineCompany) o;
        try {
            stm.executeUpdate("UPDATE Airline_Companies SET id= "+arc.id+", name=\'"+ arc.name+"\'" +
                    ",country_id="+arc.countryId+", user_id ="+arc.userId+" where id="+arc.id+"");
        } catch (SQLException e) {
            System.out.println("sorry, can't update this airline company");
            return false;
        }
        return true;
    }
    //return airline company by the country it's related to
    public AirlineCompany getAirlinesByCountry(int country_id){
        AirlineCompany airlineCompany=null;
        try {
            var result = stm.executeQuery("select * from Airline_Companies where country_id="+country_id);
            result.next();
            airlineCompany=getAirlineCompany(result);
        } catch (SQLException e) {
            System.out.println("sorry, can't get this airline company by the specific id");
        }
        return airlineCompany;
    }
    //return airline company by it's name
    public AirlineCompany getAirlineByName(String name){
        AirlineCompany airlineCompany=null;
        try {
            var result = stm.executeQuery("select * from Airline_Companies where name=\'"+name+"\'");
            result.next();
            airlineCompany=getAirlineCompany(result);
        } catch (SQLException e) {
            System.out.println("sorry, can't get this airline company by name");
        }
        return airlineCompany;
    }
    //return airline company by the username of it in the system
    public AirlineCompany getAirlineByUsername(String uesrname){
        AirlineCompany airlineCompany=null;
        try {
            var result = stm.executeQuery("select * from get_airline_by_username(\'"+uesrname+"\')");
            result.next();
            airlineCompany=new AirlineCompany(result.getLong("airline_id"),
                    result.getString("airline_name"),
                    result.getInt("airline_country_id"),
                    result.getLong("airline_user_id"));
        } catch (SQLException e) {
            System.out.println("sorry, can't get this airline company by the username");
        }
        return airlineCompany;
    }
    //return airline company by result set (help function)
    private AirlineCompany getAirlineCompany(ResultSet result){
        AirlineCompany airlineCompany= null;
        try {
            airlineCompany = new AirlineCompany(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getInt("country_id"),
                    result.getLong("user_id"));
        } catch (SQLException e) {
            System.out.println("sorry, this airline company was not found in the db");
        }
        return airlineCompany;
    }
}
