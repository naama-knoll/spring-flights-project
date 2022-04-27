package com.example.demo.Jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/contact-us", method = RequestMethod.GET)
@RestController
public class MassageController {

    @Autowired
    MassageService massageService;

    @GetMapping("/")
    public List getAll() {
        return massageService.getAll();
    }

    @GetMapping("/{id}")
    public MassageDTO get(int id) {
        var res = massageService.get(id);
        return res != null ? res : new MassageDTO();
    }

    @PostMapping("/post-massage")
    public void add(@RequestBody MassageDTO massage){
        massageService.addMassage(massage);
    }

    @DeleteMapping("/delete-massage")
    public void delete(@RequestBody MassageDTO massage){
        massageService.deleteMassage(massage);
    }
}
