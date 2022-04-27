package com.example.demo.Jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassageRepository extends CrudRepository<MassageDTO, Integer> {
    //get all
    //get id
    //update
    //delete
}
