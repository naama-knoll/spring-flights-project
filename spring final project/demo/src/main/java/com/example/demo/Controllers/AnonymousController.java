package com.example.demo.Controllers;

import com.example.demo.BusniessLogics.AnonymousFacade;
import com.example.demo.jwt.JWTUtil;
import com.example.demo.pocoPackage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class AnonymousController {

    private AnonymousFacade anonymousFacade=new AnonymousFacade();

    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;

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


    @PostMapping("/authenticate")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getUsername(),anonymousFacade.getUserId(body.getUsername()),anonymousFacade.getUserRole(body.getUsername()));

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }



    @PostMapping("/addCustomer")
    public boolean addCustomer(@RequestBody Customer customer, @RequestBody User user) {
        return anonymousFacade.addCustomer(customer, user);
    }
}
