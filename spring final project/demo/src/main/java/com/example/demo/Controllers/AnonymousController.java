package com.example.demo.Controllers;

import com.example.demo.BusniessLogics.AnonymousFacade;
import com.example.demo.BusniessLogics.LoginToken;
import com.example.demo.pocoPackage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnonymousController {

    private AnonymousFacade anonymousFacade=new AnonymousFacade();

    @GetMapping("/flights")
    public List getAllFlights() {
        return anonymousFacade.getAllFlights();
    }

    @GetMapping("/flight/{id}")
    public Flight getFlightById(@PathVariable long id) {
        var res = anonymousFacade.getFlightById(id);
        return res != null ? res : new Flight();
    }

    @GetMapping("/flightsByParameters/{origin_country}/{destination_country}/{date}")
    public List getFlightsByParameters(@PathVariable int origin_country, @PathVariable int destination_country, @PathVariable String date) {
        return anonymousFacade.getFlightsByParameters(origin_country, destination_country, date);
    }

    @GetMapping("/airline-companies")
    public List getAllAirlines() {
        return anonymousFacade.getAllAirlines();
    }

    @GetMapping("/airline-company/{id}")
    public AirlineCompany getAirlineById(@PathVariable long id) {
        var res = anonymousFacade.getAirlineById(id);
        return res != null ? res : new AirlineCompany();
    }

    @GetMapping("/airline-company/company-name={name}")
    public AirlineCompany getAirlineByParameters(@PathVariable String name) {
        var res = anonymousFacade.getAirlineByParameters(name);
        return res != null ? res : new AirlineCompany();
    }

    @GetMapping("/countries")
    public List getAllCountries() {
        return anonymousFacade.getAllCountries();
    }

    @GetMapping("/country/{id}")
    public Country getCountryById(@PathVariable int id) {
        var res = anonymousFacade.getCountryById(id);
        return res != null ? res : new Country();
    }

//    @PostMapping("login-token/")
//    public LoginToken login() {
//        //do we have to do login function???
//    }

    @PostMapping("/addCustomer")
    public boolean addCustomer(@RequestBody Customer customer, @RequestBody User user) {
        return anonymousFacade.addCustomer(customer, user);
    }
}
