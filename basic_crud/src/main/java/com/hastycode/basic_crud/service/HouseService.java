package com.hastycode.basic_crud.service;

import com.hastycode.basic_crud.model.House;
import com.hastycode.basic_crud.repo.HouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private HouseRepo repo;

    public HouseService(HouseRepo repo) {
        this.repo = repo;
    }

    public List<House> getAllHouses() {
        return repo.findAll();
    }

    public House addHouse(House house) {
        return repo.save(house);
    }


    public House updateHouse(int number, House house) {
        return repo.save(house);
    }

    public void deleteHouse(int number) {
        repo.deleteById(number);
    }

    public House getHouseByNumber(int number) {
        return repo.findById(number).orElse(null);
    }
}
