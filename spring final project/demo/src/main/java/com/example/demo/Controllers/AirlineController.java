package com.example.demo.Controllers;

import com.example.demo.BusniessLogics.*;
import com.example.demo.daoPackage.UsersDAO;
import com.example.demo.pocoPackage.AirlineCompany;
import com.example.demo.pocoPackage.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RequestMapping(value = "/airline-company", method = RequestMethod.GET)
@RestController
public class AirlineController {

    private AirlineFacade airlineFacade;


    @PostMapping("/authenticate")
    public void authenticate() throws Exception {
        var username =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var role= SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        var role2=role.replace("ROLE_","");
        if(!role2.equals("AIRLINE_COMPANY")){
            throw new Exception("invalid role!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        UserRole final_role=UserRole.AIRLINE_COMPANY;
        UsersDAO usersDAO=new UsersDAO();
        System.out.println(usersDAO.getUserByUsername(username).id);
        System.out.println(username);
        System.out.println(final_role);

        LoginToken loginToken=new LoginToken(usersDAO.getUserByUsername(username).id,username,final_role);
        airlineFacade=new AirlineFacade(loginToken);
    }


    @GetMapping("/airline-flights")
    public List getMyFlights() {
        return airlineFacade.getMyFlights();
    }

    @PutMapping("/update-airline")
    public boolean updateAirline(@RequestBody AirlineCompany airlineCompany) {
        return airlineFacade.updateAirline(airlineCompany);
        //w
    }

    @PostMapping("/add-flight")
    public boolean addFlight(@RequestBody Flight flight) {
        return airlineFacade.addFlight(flight);
        //w
    }


    @PutMapping("/update-flight")
    public boolean updateFlight(@RequestBody Flight flight) {
        return airlineFacade.updateFlight(flight);
        //w
    }

    @DeleteMapping("cancel-flight")
    public boolean removeFlight(@RequestBody Flight flight) {
        return airlineFacade.removeFlight(flight);
        //w
    }

}
