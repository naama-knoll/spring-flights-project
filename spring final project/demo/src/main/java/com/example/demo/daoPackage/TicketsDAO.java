package com.example.demo.daoPackage;

import com.example.demo.pocoPackage.Flight;
import com.example.demo.pocoPackage.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAO implements DAO{
    List<Ticket> ticketList=new ArrayList<>();
    //return ticket by id
    @Override
    public Ticket get(long id) {
        Ticket ticket=null;
        try {
            var result=stm.executeQuery("select * from Tickets where id="+id);
            result.next();
            ticket=getTicket(result);
            result.close();
        } catch (SQLException e) {
            System.out.println("sorry can't get the ticket");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return ticket;
    }
    //return list of tickets
    @Override
    public List getAll() {
        try {
            var result =stm.executeQuery("select * from Tickets");
            while(result.next()){
                ticketList.add(getTicket(result));
            }
            result.close();
        } catch (SQLException e) {
            System.out.println("something went wrong ");
        }catch (Exception e1){
            e1.getMessage();
            System.out.println("something was wrong with the close");
        }
        return ticketList;
    }
    //add a ticket
    @Override
    public boolean add(Object o) {
        Ticket ticket= (Ticket) o;
        try {
            stm.executeUpdate("insert into Tickets (flight_id,coustomer_id) " +
                    "values ( "+ticket.flightId+","+ticket.customerId+")");
        } catch (SQLException e) {
            System.out.println("sorry,you can't add the ticket ");
            return false;
        }
        return true;
    }
    //remove ticket
    @Override
    public boolean remove(Object o) {
        Ticket ticket= (Ticket) o;
        try {
            stm.executeUpdate("DELETE from Tickets where id="+ticket.id);
        } catch (SQLException e) {
            System.out.println("sorry,you can't remove the ticket ");
            return false;
        }
        return true;
    }
    //update ticket
    @Override
    public boolean update(Object o) {
        Ticket ticket= (Ticket) o;
        try {
            stm.executeUpdate("UPDATE Tickets SET id= "+ticket.id+",flight_id="+ticket.flightId+",coustomer_id="+ticket.customerId +
                    "where id="+ticket.id);
        } catch (SQLException e) {
            System.out.println("sorry,you can't update the ticket ");
            return false;
        }
        return true;
    }
    //return list of tickets of specific customer by his id
    public List getTicketByCustomer(long customerId){
        List<Ticket> tickets=new ArrayList<>();
        try {
            var result =stm.executeQuery("select * from get_tickets_by_customer("+customerId+")");
            while(result.next()){
                tickets.add(getTicket(result));
            }
        } catch (SQLException e) {
            System.out.println("something went wrong ");
        }
        return tickets;
    }
    //returns ticket by result set (help function )
    private Ticket getTicket(ResultSet result){
        Ticket ticket=null;
        try {
            ticket=new Ticket(result.getLong("id"),
                    result.getLong("flight_id"),
                    result.getLong("coustomer_id"));
        } catch (SQLException e) {
            System.out.println("sorry , the ticket was not found in the db");
        }
        return ticket;
    }
}
