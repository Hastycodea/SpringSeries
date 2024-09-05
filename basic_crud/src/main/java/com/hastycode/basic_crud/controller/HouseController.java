package com.hastycode.basic_crud.controller;

import com.hastycode.basic_crud.model.House;
import com.hastycode.basic_crud.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HouseController {

    private final HouseService service;

    public HouseController(HouseService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome home!";
    }

    @GetMapping("/houses")
    public List<House> getAllHouses() {
        return service.getAllHouses();
    }

    @GetMapping("/houses/{number}")
    public House getHouseByNumber(@PathVariable int number) {
        House house = null;
        house = service.getHouseByNumber(number);
        return house;
    }

    @PostMapping("/houses")
    public House addHouse(@RequestBody House house) {
        return service.addHouse(house);
    }

    @PutMapping("/houses/{number}")
    public House updateHouse(@PathVariable int number, @RequestBody House house) {
        House newHouse = null;
        newHouse = service.updateHouse(number, house);
        return newHouse;
    }

    @DeleteMapping("/houses/{number}")
    public void deleteHouse(@PathVariable int number) {
        House house = service.getHouseByNumber(number);

        if (house != null) {
            service.deleteHouse(number);
        }
    }

}
