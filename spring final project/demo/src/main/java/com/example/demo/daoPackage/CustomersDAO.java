package com.example.demo.daoPackage;

import com.example.demo.pocoPackage.Country;
import com.example.demo.pocoPackage.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAO implements DAO{
    public List<Customer>customerList=new ArrayList<>();

    //return customer by id
    @Override
    public Customer get(long id) {
        Customer customer=null;
        try {
            var result = stm.executeQuery("select * from Customers where id="+id);
            result.next();
            customer=getCustomer(result);
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry, can't get this customer");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return customer;
    }
    //return list of customers
    @Override
    public List getAll() {
        try {
            var result=stm.executeQuery("select * from Customers");
            while(result.next()){
                customerList.add(getCustomer(result));
            }
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry , some problem occurred");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return customerList;
    }
    //add a customer
    @Override
    public boolean add(Object o) {
        Customer cm= (Customer) o;
        try {
            stm.executeUpdate("insert into Customers (first_name,last_name,address,phone_no,credit_card_no,user_id) " +
                    "values (\'"+cm.firstName+"\',\'"+cm.lastName+"\',\'"+cm.address+"\',\'"+cm.phoneNumber+"\',\'"+cm.creditCardNumber+"\',"+cm.userId+")");
        } catch (SQLException e) {
            System.out.println("sorry,you can't add the customer ");
            return false;
        }
        return true;
    }
    //remove a customer
    @Override
    public boolean remove(Object o) {
        Customer cm= (Customer) o;
        try {
            stm.executeUpdate("DELETE from Customers WHERE id = "+cm.id);
        } catch (SQLException e) {
            System.out.println("sorry,you can't remove the customer ");
            return false;
        }
        return true;
    }
    //update customer
    @Override
    public boolean update(Object o) {
        Customer cm= (Customer) o;
        try {
            stm.executeUpdate("UPDATE Customers SET id= "+cm.id+", first_name=\'"+ cm.firstName+"\', last_name=\'" +cm.lastName
                    +"\', address=\'"+cm.address+"\',phone_no=\'"+cm.phoneNumber+"\',credit_card_no=\'"+cm.creditCardNumber
                    +"\',user_id="+cm.userId+
                    " where id="+cm.id);
        } catch (SQLException e) {
            System.out.println("sorry, can't update this customer");
            return false;
        }
        return true;
    }
    //return customer by it's username in the system
    public Customer getCustomerByUsername(String username){
        Customer customer=null;
        try {
            var result = stm.executeQuery("select * from get_customer_by_username(\'"+username+"\')");
            result.next();
            customer=new Customer(result.getLong("_id"),
                    result.getString("_first_name"),
                    result.getString("_last_name"),
                    result.getString("_address"),
                    result.getString("_phone_no"),
                    result.getString("_credit_card_no"),
                    result.getLong("_user_id"));
        } catch (SQLException e) {
            System.out.println("sorry, can't get this customer by username");
        }
        return customer;
    }
    // return customer by result set (help function)
    private Customer getCustomer(ResultSet result){
        Customer cm=null;
        try {
            cm=new Customer(result.getLong("id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("address"),
                    result.getString("phone_no"),
                    result.getString("credit_card_no"),
                    result.getLong("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cm;
    }
}
