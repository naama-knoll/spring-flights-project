package com.example.demo.Controllers;

import com.example.demo.BusniessLogics.*;
import com.example.demo.daoPackage.UsersDAO;
import com.example.demo.pocoPackage.Customer;
import com.example.demo.pocoPackage.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value = "/customer", method = RequestMethod.GET)
@RestController
public class CustomerController {

    private CustomerFacade customerFacade;

    @PostMapping("/authenticate")
    public void authenticate() throws Exception {
        var username =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var role= SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        var role2=role.replace("ROLE_","");
        if(!role2.equals("CUSTOMER")){
            throw new Exception("invalid role!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        UserRole final_role=UserRole.CUSTOMER;
        UsersDAO usersDAO=new UsersDAO();
        System.out.println(usersDAO.getUserByUsername(username).id);
        System.out.println(username);
        System.out.println(final_role);

        LoginToken loginToken=new LoginToken(usersDAO.getUserByUsername(username).id,username,final_role);
        customerFacade=new CustomerFacade(loginToken);
    }


    @PutMapping("/update-customer")
    public boolean updateCustomer(@RequestBody Customer customer){
        return customerFacade.updateCustomer(customer);
        //work
    }

    @PostMapping("/buy-ticket")
    public boolean addTicket(@RequestBody Ticket ticket){
        return customerFacade.addTicket(ticket);
        //w
    }

    @DeleteMapping("/cancel-ticket")
    public boolean removeTicket(@RequestBody Ticket ticket){
        return customerFacade.removeTicket(ticket);
        //w
    }

    @GetMapping("/tickets")
    public List getMyTickets(){
        return customerFacade.getMyTickets();
    }






}
