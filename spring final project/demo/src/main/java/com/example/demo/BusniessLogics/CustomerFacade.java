package com.example.demo.BusniessLogics;

import com.example.demo.daoPackage.CustomersDAO;
import com.example.demo.daoPackage.FlightsDAO;
import com.example.demo.daoPackage.TicketsDAO;
import com.example.demo.pocoPackage.Customer;
import com.example.demo.pocoPackage.Flight;
import com.example.demo.pocoPackage.Ticket;

import java.util.ArrayList;
import java.util.List;


public class CustomerFacade extends AnonymousFacade{
    //attributes
    private LoginToken token;
    private Customer customer;
    private CustomersDAO customerDAO = new CustomersDAO();
    private TicketsDAO ticketDAO=new TicketsDAO();
    private FlightsDAO flightsDAO=new FlightsDAO();


    public CustomerFacade(LoginToken token) {
        this.token = token;
        this.customer=customerDAO.getCustomerByUsername(token.name);
    }

    public CustomerFacade() {
        this.token=new LoginToken(3,"Blisse",UserRole.CUSTOMER);
        this.customer=customerDAO.getCustomerByUsername(token.name);
    }

    //update customer
    public boolean updateCustomer(Customer customer) {
        if(this.customer.id==customer.id && this.token.id==customer.userId && this.token.role==UserRole.CUSTOMER ){
            customerDAO.update(customer);
            return true;
        }
        printError("updating","customer");
        return false;
    }
    //buy a ticket
    public boolean addTicket(Ticket ticket){
        Flight f=flightsDAO.get(ticket.flightId);
        if(this.customer.id==ticket.customerId && this.token.role==UserRole.CUSTOMER && f.remainingTickets>0){
            ticketDAO.add(ticket);
            //update remaining tickets -1 if exists
            flightsDAO.updateRemainingTicketsMinus(ticket.flightId);
        }
        printError("adding","ticket");
        return false;
    }
    //remove ticket
    public boolean removeTicket(Ticket ticket) {
        if(this.customer.id==ticket.customerId && this.token.role==UserRole.CUSTOMER) {
            ticketDAO.remove(ticket);
            //update remaining tickets+1
            flightsDAO.updateRemainingTicketsPlus(ticket.flightId);
            return true;
        }
        printError("removing","ticket");
        return false;
    }
    //get all tickets of the customer
    public List getMyTickets(){
        if(this.token.role==UserRole.CUSTOMER)
            return ticketDAO.getTicketByCustomer(this.customer.id);
        return new ArrayList<>();
    }
}
