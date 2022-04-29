package com.example.demo.Controllers;

import com.example.demo.BusniessLogics.AdministratorFacade;
import com.example.demo.BusniessLogics.AirlineFacade;
import com.example.demo.BusniessLogics.LoginToken;
import com.example.demo.BusniessLogics.UserRole;
import com.example.demo.daoPackage.UsersDAO;
import com.example.demo.pocoPackage.Administrator;
import com.example.demo.pocoPackage.AirlineCompany;
import com.example.demo.pocoPackage.Customer;
import com.example.demo.pocoPackage.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/admin", method = RequestMethod.GET)
@RestController
public class AdminController {


    private AdministratorFacade adminFacade;

    @PostMapping("/authenticate")
    public void authenticate() throws Exception {
        var username =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var role= SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        var role2=role.replace("ROLE_","");
        if(!role2.equals("ADMIN")){
            throw new Exception("invalid role!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        UserRole final_role=UserRole.ADMIN;
        UsersDAO usersDAO=new UsersDAO();
        System.out.println(usersDAO.getUserByUsername(username).id);
        System.out.println(username);
        System.out.println(final_role);

        LoginToken loginToken=new LoginToken(usersDAO.getUserByUsername(username).id,username,final_role);
        adminFacade=new AdministratorFacade(loginToken);
    }

    @GetMapping("/customers")
    public List getAllCustomers(){
        return adminFacade.getAllCustomers();
    }

    @PostMapping("/add-airline-company")
    public boolean addAirlineCompany(@RequestBody AirlineCompany airlineCompany,@RequestBody User user){
        return adminFacade.addAirlineCompany(airlineCompany,user);
        //w
    }

    @PostMapping("/add-customer")
    public boolean addCustomer(@RequestBody Customer customer,@RequestBody User user){
        return adminFacade.addCustomer(customer,user);
        //w
    }

    @PostMapping("/add-admin")
    public boolean addAdmin(@RequestBody Administrator administrator,@RequestBody User user){
        return adminFacade.addAdministrator(administrator,user);
        //w
    }

    @DeleteMapping("/remove-admin")
    public boolean removeAdmin(@RequestBody Administrator administrator){
       return adminFacade.removeAdministrator(administrator);
       //w
    }

    @DeleteMapping("/remove-customer")
    public boolean removeCustomer(@RequestBody Customer customer){
        return adminFacade.removeCustomer(customer);
        //w
    }

    @DeleteMapping("/remove-airline-company")
    public boolean removeAirlineCompany(@RequestBody AirlineCompany airlineCompany){
        return adminFacade.removeAirlineCompany(airlineCompany);
        //w
    }
}
