package com.example.demo.Controllers;

import com.example.demo.BusniessLogics.CustomerFacade;
import com.example.demo.pocoPackage.Customer;
import com.example.demo.pocoPackage.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value = "/customer", method = RequestMethod.GET)
@RestController
public class CustomerController {

    private CustomerFacade customerFacade=new CustomerFacade();

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
