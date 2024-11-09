package com.hms.service;

import com.hms.entity.City;
import com.hms.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addNewCity(City city) {
        City newCity = cityRepository.save(city);
        return newCity;
    }

    public List<City> getCityList() {
        List<City> cityList= cityRepository.findAll();
        return cityList;
    }

    public void deleteCity(long id) {
        cityRepository.deleteById(id);
    }
}
