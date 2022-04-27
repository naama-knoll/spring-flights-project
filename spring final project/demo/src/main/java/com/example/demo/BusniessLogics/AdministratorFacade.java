package com.example.demo.BusniessLogics;

import com.example.demo.daoPackage.AdministratorsDAO;
import com.example.demo.daoPackage.AirlineCompaniesDAO;
import com.example.demo.daoPackage.CustomersDAO;
import com.example.demo.daoPackage.UsersDAO;
import com.example.demo.pocoPackage.Administrator;
import com.example.demo.pocoPackage.AirlineCompany;
import com.example.demo.pocoPackage.Customer;
import com.example.demo.pocoPackage.User;

import java.util.ArrayList;
import java.util.List;

public class AdministratorFacade extends AnonymousFacade{
    public LoginToken token;
    public CustomersDAO customersDAO=new CustomersDAO();
    public AdministratorsDAO administratorsDAO=new AdministratorsDAO();
    public AirlineCompaniesDAO airlineCompaniesDAO=new AirlineCompaniesDAO();
    public UsersDAO usersDAO=new UsersDAO();

    public AdministratorFacade(LoginToken token) {
        this.token = token;
    }

    public AdministratorFacade(){
        this.token=new LoginToken(2,"yehuda",UserRole.ADMIN);
    }

    //get all the customers in the system
    public List getAllCustomers(){
        if(this.token.role==UserRole.ADMIN) {
            return customersDAO.getAll();
        }
        return new ArrayList<>();
    }

    //add airline company
    public boolean addAirlineCompany(AirlineCompany airlineCompany,User user){
        if(this.token.role==UserRole.ADMIN) {
            var isAdded=super.createNewUser(user);
            if(isAdded){
                User user1 = usersDAO.getUserByUsername(user.username);
                isAdded=airlineCompaniesDAO.add(new AirlineCompany(airlineCompany.id,airlineCompany.name,airlineCompany.countryId,user1.id));
                if(!isAdded){
                    usersDAO.remove(user);
                    return false;
                }
            }
            return true;
        }
        printError("adding","airline company");
        return false;
    }

    //add customer
    public boolean addCustomer(Customer customer,User user){
        if(this.token.role==UserRole.ADMIN) {
            return super.addCustomer(customer,user);
        }
        printError("adding","customer");
        return false;
    }

    //add admin
    public boolean addAdministrator(Administrator administrator,User user){
        if(this.token.role==UserRole.ADMIN) {
            var isAdded=super.createNewUser(user);
            if(isAdded) {
                User user1 = usersDAO.getUserByUsername(user.username);
                isAdded=administratorsDAO.add(new Administrator(administrator.id,administrator.firstName,administrator.lastName,user1.id));
                if(!isAdded){
                    usersDAO.remove(user);
                    printError("adding","admin");
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    //remove administrator
    public boolean removeAdministrator(Administrator administrator){
        if(this.token.role==UserRole.ADMIN) {
            var isRemoved=administratorsDAO.remove(administrator);
            User user=usersDAO.get(administrator.userId);
            if(isRemoved) {
                return usersDAO.remove(user);
            }
        }
        printError("removing","admin");
        return false;
    }

    //remove customer
    public boolean removeCustomer(Customer customer){
        if(this.token.role==UserRole.ADMIN) {
            var isRemoved=customersDAO.remove(customer);
            User user=usersDAO.get(customer.userId);
            if(isRemoved) {
                return usersDAO.remove(user);
            }
        }
        printError("removing","customer");
        return false;
    }

    //remove airline company
    public boolean removeAirlineCompany(AirlineCompany airlineCompany){
        if(this.token.role==UserRole.ADMIN) {
            var isRemoved=airlineCompaniesDAO.remove(airlineCompany);
            User user=usersDAO.get(airlineCompany.userId);
            if(isRemoved) {
                return usersDAO.remove(user);
            }
        }
        printError("removing","airline company");
        return false;
    }
}
