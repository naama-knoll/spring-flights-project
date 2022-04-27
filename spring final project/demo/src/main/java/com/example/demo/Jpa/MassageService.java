package com.example.demo.Jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MassageService {

    @Autowired
    MassageRepository massageRepository;

    public List<MassageDTO> getAll(){
        List<MassageDTO> massages=new ArrayList<>();
        massageRepository.findAll().forEach(massages::add);
        return massages;
    }

    public MassageDTO get(int id){
        var res=massageRepository.findById(id);
        return res.orElse(null);
    }

    public void addMassage(MassageDTO massage){
        massageRepository.save(massage);
    }

    public void deleteMassage(MassageDTO massage){
        massageRepository.delete(massage);
    }

//    public void updateMassage(){
//
//    }
}
