package com.hms.service;

import com.hms.entity.City;
import com.hms.entity.Country;
import com.hms.entity.Property;
import com.hms.entity.State;
import com.hms.payload.PropertyDto;
import com.hms.repository.CityRepository;
import com.hms.repository.CountryRepository;
import com.hms.repository.PropertyRepository;
import com.hms.repository.StateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;
    private CountryRepository countryRepository;
    private StateRepository stateRepository;
    private CityRepository cityRepository;

    public PropertyService
            (PropertyRepository propertyRepository, ModelMapper modelMapper, CountryRepository countryRepository, StateRepository stateRepository, CityRepository cityRepository) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }
    public Property addNewProperty(long country_id, long state_id, long city_id, Property property) {
        Country country = countryRepository.findById(country_id).get();
        State state = stateRepository.findById(state_id).get();
        City city = cityRepository.findById(city_id).get();

        property.setCountry(country);
        property.setState(state);
        property.setCity(city);

        Property addedProperty =  propertyRepository.save(property);
        return addedProperty;
    }

    public List<Property> getPropertyList() {
        List<Property> propertyList = propertyRepository.findAll();
        return propertyList;
    }

    Property mapToEntity(PropertyDto propertyDto){
        Property property = modelMapper.map(propertyDto, Property.class);
        return property;
    }

    PropertyDto mapToDto(Property property){
        PropertyDto dto = modelMapper.map(property, PropertyDto.class);
        return dto;
    }

    public Property updatePropertyDetails(long id, long country_id, long state_id, long city_id, Property property) {
        Property p = propertyRepository.findById(id).get();

        Country country = countryRepository.findById(country_id).get();
        State state = stateRepository.findById(state_id).get();
        City city = cityRepository.findById(city_id).get();

        p.setName(property.getName());
        p.setNo_of_rooms(property.getNo_of_rooms());
        p.setNo_of_bathrooms(property.getNo_of_bathrooms());
        p.setNo_of_beds(property.getNo_of_beds());
        p.setCountry(country);
        p.setState(state);
        p.setCity(city);
        Property property1 =  propertyRepository.save(p);
        return property1;
    }

    public void deletePropertyById(long id) {
        propertyRepository.deleteById(id);
    }

    public Property getPropertyById(long id) {
        Property property = propertyRepository.findById(id).get();
        return property;
    }
}
