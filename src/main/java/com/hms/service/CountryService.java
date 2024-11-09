package com.hms.service;

import com.hms.entity.Country;
import com.hms.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addNewCountry(Country country) {
        Country newCountry = countryRepository.save(country);
        return newCountry;
    }

    public List<Country> getCountryList() {
        List<Country> cntryList = countryRepository.findAll();
        return cntryList;
    }

    public void deleteCountry(long id) {
        countryRepository.deleteById(id);
    }
}
