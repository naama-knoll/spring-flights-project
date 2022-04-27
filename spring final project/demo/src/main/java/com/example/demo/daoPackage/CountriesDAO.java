package com.example.demo.daoPackage;

import com.example.demo.pocoPackage.Administrator;
import com.example.demo.pocoPackage.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesDAO implements DAO{
    public List<Country> countryList=new ArrayList<>();
    //return country by id
    @Override
    public Country get(long id) {
        Country country=null;
        try {
            var result = stm.executeQuery("select * from Countries where id="+id);
            result.next();
            country=getCountry(result);
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry, can't get this country");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return country;
    }
    //return list of all the countries
    @Override
    public List getAll() {
        try {
            var result=stm.executeQuery("select * from Countries");
            while(result.next()){
                countryList.add(getCountry(result));
            }
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry , some problem occurred");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return countryList;
    }
    //add a country
    @Override
    public boolean add(Object o) {
        Country cn= (Country) o;
        try {
            stm.executeUpdate("insert into Countries (name) values ( \'"+cn.name+"\')");
        } catch (SQLException e) {
            System.out.println("sorry,you can't add the country ");
            return false;
        }
        return true;
    }
    //remove a country given
    @Override
    public boolean remove(Object o) {
        Country cn= (Country) o;
        try {
            stm.executeUpdate("DELETE from Countries WHERE id = "+cn.id);
        } catch (SQLException e) {
            System.out.println("sorry,you can't remove the country ");
            return false;
        }
        return true;
    }
    //update country
    @Override
    public boolean update(Object o) {
        Country cn= (Country) o;
        try {
            stm.executeUpdate("UPDATE Countries SET id= "+cn.id+", name=\'"+ cn.name+"\'" +
                    " where id="+cn.id+"");
        } catch (SQLException e) {
            System.out.println("sorry, can't update this admin");
            return false;
        }
        return true;
    }
    //return country by result set (help function)
    private Country getCountry(ResultSet result){
        Country country= null;
        try {
            country = new Country(result.getInt("id"),
                    result.getString("name"));
        } catch (SQLException e) {
            System.out.println("this country was not found in the db");
        }
        return country;
    }
}
