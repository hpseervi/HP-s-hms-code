package com.hms.controller;

import com.hms.entity.City;
import com.hms.repository.CountryRepository;
import com.hms.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    private CityService cityService;
    private final CountryRepository countryRepository;

    public CityController(CityService cityService,
                          CountryRepository countryRepository) {
        this.cityService = cityService;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/addCity")
    public ResponseEntity<?> addCountry(
            @RequestBody City city
    ){
        City addedCity = cityService.addNewCity(city);
        return new ResponseEntity<>(addedCity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<City>> getCityList(){
        List<City> cityList = cityService.getCityList();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCity(
            @RequestParam long id
    ){
        cityService.deleteCity(id);
        return new ResponseEntity<>("City deleted", HttpStatus.OK);
    }
}
