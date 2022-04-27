package com.example.demo.daoPackage;

import com.example.demo.pocoPackage.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements DAO{
    List<User> userList=new ArrayList<>();
    //return user by id
    @Override
    public User get(long id) {
        User user=null;
        try {
            var result=stm.executeQuery("select * from Users where id="+id);
            result.next();
            user=getUser(result);
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry can't get the user");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return user;
    }
    //return list of users
    @Override
    public List getAll() {
        try {
            var result =stm.executeQuery("select * from Users");
            while(result.next()){
                userList.add(getUser(result));
            }
            result.close();
        } catch (SQLException e) {
            System.out.println("something went wrong ");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return userList;
    }
    //add new user
    @Override
    public boolean add(Object o) {
        User user= (User) o;
        try {
            stm.executeUpdate("insert into Users (username,password,email,user_role) " +
                    "values (\'"+user.username+"\',\'"+user.password+"\',\'"+user.email+"\',"+user.userRole+")");
        } catch (SQLException e) {
            System.out.println("sorry,you can't add the user ");
            return false;
        }
        return true;
    }
    //remove user
    @Override
    public boolean remove(Object o) {
        User user= (User) o;
        try {
            stm.executeUpdate("DELETE from Users where id="+user.id);
        } catch (SQLException e) {
            System.out.println("sorry,you can't remove the user ");
            return false;
        }
        return true;
    }
    //update exist user
    @Override
    public boolean update(Object o) {
        User user= (User) o;
        try {
            stm.executeUpdate("UPDATE Users SET id= "+user.id+",username=\'"+user.username+"\',password=\'"+user.password +"\',email=\'"+user.email+"\',user_role="+user.userRole+
                    "where id="+user.id);
        } catch (SQLException e) {
            System.out.println("sorry,you can't update the ticket ");
            return false;
        }
        return true;
    }
    //return user by username
    public User getUserByUsername(String username){
        User user=null;
        try {
            var result=stm.executeQuery("select * from get_user_by_username(\'"+username+"\')");
            result.next();
            user=new User(result.getLong("_id"),
                    result.getString("_userName"),
                    result.getString("_password"),
                    result.getString("_email"),
                    result.getInt("_use_role"));
        } catch (SQLException e) {
            System.out.println("sorry can't get the user by username");
        }
        return user;
    }
    //return user by result set (help function)
    private User getUser(ResultSet result){
        User user=null;
        try {
            user=new User(result.getLong("id"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getString("email"),
                    result.getInt("user_role"));
        } catch (SQLException e) {
            System.out.println("something went wrong with getting the user");
        }
        return user;
    }

    //join user and user role
    public String joinUserWithUserRole(String username){
        ResultSet result=null;
        String role=null;
        try {
            result= stm.executeQuery("select User_Roles.role_name from Users join User_Roles on Users.user_role=User_Roles.id where Users.username="+username);
            result.next();
            role=result.getString("role_name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
