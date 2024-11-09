package com.hms.controller;

import com.hms.entity.Property;
import com.hms.repository.PropertyRepository;
import com.hms.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;
    private PropertyRepository propertyRepository;

    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<?> addProperty(
            @RequestParam long country_id,
            @RequestParam long state_id ,
            @RequestParam long city_id ,
            @RequestBody Property property
            ) {
        Optional<Property> opName = propertyRepository.findByName(property.getName());
        if (opName.isPresent()) {
            return new ResponseEntity<>("This property is already added", HttpStatus.CONFLICT);
        }else {
            Property addedProperty = propertyService.addNewProperty(country_id, state_id, city_id, property);
            return new ResponseEntity<>(addedProperty, HttpStatus.CREATED);
        }
    }

    @GetMapping("/propertyList")
    public ResponseEntity<List<Property>> getPropertyList (){
        List<Property> list = propertyService.getPropertyList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Property> updateProperty (
        @PathVariable long id,
        @RequestParam long country_id,
        @RequestParam long state_id ,
        @RequestParam long city_id ,
        @RequestBody Property property
    ){
        Property updatedProperty = propertyService.updatePropertyDetails(id, country_id, state_id, city_id, property);
        return new ResponseEntity<>(updatedProperty, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProperty(
            @RequestParam long id
    ){
        propertyService.deletePropertyById(id);
        return new ResponseEntity<>("Property deleted", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Property> getPropertyById(
            @PathVariable long id
    ){
        Property property = propertyService.getPropertyById(id);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }
}
