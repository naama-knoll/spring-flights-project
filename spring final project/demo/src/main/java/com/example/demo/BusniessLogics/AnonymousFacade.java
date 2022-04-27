package com.example.demo.BusniessLogics;

import com.example.demo.daoPackage.CustomersDAO;
import com.example.demo.daoPackage.UsersDAO;
import com.example.demo.pocoPackage.Customer;
import com.example.demo.pocoPackage.User;

public class AnonymousFacade extends FacadeBase{
    public AnonymousFacade() {
    }

    public User user;
    //search for the username in the db by the dao if exists check the password and login
    public LoginToken login (String username,String password){
        UsersDAO usersDAO=new UsersDAO();
        this.user =usersDAO.getUserByUsername(username);
        if(this.user != null && this.user.password.equals(password)){
            UserRole userRole=updateRole(this.user.userRole);
            LoginToken token=new LoginToken(this.user.id,this.user.username,userRole);
            return token;
        }
        return new LoginToken();
    }
    // update the role
    private UserRole updateRole(int role){
        UserRole userRole=null;
        switch (role){
            case 1:
                userRole=UserRole.CUSTOMER;
                break;
            case 2:
                userRole=UserRole.AIRLINE_COMPANY;
                break;
            case 3:
                userRole=UserRole.ADMIN;
                break;
        }
        return userRole;
    }
    // add new customer == register / sign up
    public boolean addCustomer(Customer customer,User user){
        if(user.userRole==1) {
            var isAdded=super.createNewUser(user);
            if(isAdded) {
                UsersDAO usersDAO = new UsersDAO();
                User user1 = usersDAO.getUserByUsername(user.username);
                CustomersDAO customersDAO = new CustomersDAO();
                isAdded=customersDAO.add(new Customer(customer.id, customer.firstName, customer.lastName, customer.address, customer.phoneNumber, customer.creditCardNumber, user1.id));
                if(!isAdded){
                    usersDAO.remove(user);
                    System.out.println("user and customer was not added");
                    return false;
                }
                return true;
            }
        }else {
            try {
                throw new Exception("you are able to add only customer type of user");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return false;
    }
}
