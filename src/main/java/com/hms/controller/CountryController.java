package com.hms.controller;

import com.hms.entity.Country;
import com.hms.repository.CountryRepository;
import com.hms.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    private CountryService countryService;
    private final CountryRepository countryRepository;

    public CountryController(CountryService countryService,
                             CountryRepository countryRepository) {
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(
            @RequestBody Country country
    ){
        Country addedCountry = countryService.addNewCountry(country);
        return new ResponseEntity<>(addedCountry, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountryList(){
        List<Country> cntryList = countryService.getCountryList();
        return new ResponseEntity<>(cntryList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCountry (
            @RequestParam long id
    ){
            countryService.deleteCountry(id);
            return new ResponseEntity<>("Country deleted along with all related data", HttpStatus.OK);
    }
}
