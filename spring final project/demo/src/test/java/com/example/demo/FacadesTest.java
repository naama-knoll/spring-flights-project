package com.example.demo;

import com.example.demo.pocoPackage.Customer;
import com.example.demo.pocoPackage.Flight;
import com.example.demo.pocoPackage.Ticket;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.net.PortUnreachableException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class FacadesTest {


    @Test
    public void getFlightByIdForAnonymusTest() {
        var current = new Flight(1, 1, 1, 2, "2022-04-12 12:20:20", "2022-04-13 00:00:00", 30);
        var expected = GenerateDTOforTesting.test1("http://localhost:8080/flight/1", Flight.class);
        Assert.assertEquals(current, expected);
    }

    @Test
    public void getCustomersForAdminTest() {
        var current = new Customer(1, "blisee", "bil", "ramat-aviv", "0523556609", "4580765478983456", 4);
        var customersList = GenerateDTOforTesting.test1("http://localhost:8080/admin/customers/", Customer[].class);
        var s = Arrays.stream(customersList).filter((c) -> c.id == 1).findFirst().get();
        Assert.assertEquals(current, s);
    }

    @Test
    public void getMyFlightsForAirlineTest() {
        var current = new Flight(1, 1, 1, 2, "2022-04-12 12:20:20", "2022-04-13 00:00:00", 30);
        var flightList = GenerateDTOforTesting.test1("http://localhost:8080/airline-company/airline-flights/", Flight[].class);
        //var expected=flightList[0];
        var s = Arrays.stream(flightList).filter((c) -> c.id == 1).findFirst().get();
        Assert.assertEquals(current, s);
    }

    @Test
    public void getMyTicketsForCustomer() {
        var current = new Ticket(3, 3, 1);
        var tickets = GenerateDTOforTesting.test1("http://localhost:8080/customer/tickets", Ticket[].class);
        var expected = Arrays.stream(tickets).filter((t) -> t.id == 3).findFirst().get();
        Assert.assertEquals(current, expected);
    }

}
