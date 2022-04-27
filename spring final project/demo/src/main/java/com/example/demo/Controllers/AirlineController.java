package com.example.demo.Controllers;

import com.example.demo.BusniessLogics.AirlineFacade;
import com.example.demo.pocoPackage.AirlineCompany;
import com.example.demo.pocoPackage.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/airline-company", method = RequestMethod.GET)
@RestController
public class AirlineController {

    private AirlineFacade airlineFacade=new AirlineFacade();

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
