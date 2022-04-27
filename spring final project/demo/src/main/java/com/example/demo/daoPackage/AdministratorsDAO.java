package com.example.demo.daoPackage;

import com.example.demo.pocoPackage.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorsDAO implements DAO{
    public List<Administrator> administratorsList=new ArrayList<>();
    //get admin by id
    @Override
    public Administrator get(long id) {
        Administrator administrator=null;
        try {
            var result = stm.executeQuery("select * from Adminstrators where id="+id);
            result.next();
            administrator=getAdministrator(result);
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry, can't get this admin");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return administrator;
    }
    // get list of all the admins
    @Override
    public List getAll() {
        try {
            var result=stm.executeQuery("select * from Adminstrators");
            while(result.next()){
                administratorsList.add(getAdministrator(result));
            }
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry , some problem occurred");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return administratorsList;
    }
    //add new admin
    @Override
    public boolean add(Object o) {
        Administrator adm= (Administrator) o;
        try {
            stm.executeUpdate("insert into Adminstrators (first_name,last_name,user_id) values (\'"+adm.firstName+"\',\'"+adm.lastName+"\',"+adm.userId+")");
        } catch (SQLException e) {
            System.out.println("sorry,you can't add the admin ");
            return false;
        }
        return true;
    }
    //remove a admin
    @Override
    public boolean remove(Object o) {
        Administrator adm= (Administrator) o;
        try {
            stm.executeUpdate("DELETE from Adminstrators WHERE id = "+adm.id);
        }
        catch (SQLException e){
            System.out.println("sorry, you can't remove this admin");
            return false;
        }
        return true;
    }
    //update values of given admin
    @Override
    public boolean update(Object o) {
        Administrator adm= (Administrator) o;
        try {
            stm.executeUpdate("UPDATE Adminstrators SET id= "+adm.id+", first_name=\'"+ adm.firstName+"\'" +
                    ",last_name=\'"+adm.lastName+"\', user_id ="+adm.userId+" where id="+adm.id+"");
        } catch (SQLException e) {
            System.out.println("sorry, can't update this admin");
            return false;
        }
        return true;
    }
    //return adminstrator by the result set
    private Administrator getAdministrator(ResultSet result){
        Administrator adm= null;
        try {
            adm = new Administrator(result.getInt("id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getLong("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adm;
    }

}
