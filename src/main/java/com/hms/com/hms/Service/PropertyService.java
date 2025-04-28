//package com.hms.com.hms.Service;
//
//import com.hms.com.hms.Entity.Property;
//import com.hms.com.hms.Repository.PropertyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PropertyService {
//
//
//    private PropertyRepository propertyRepository;
//    @Autowired
//    public PropertyService(PropertyRepository propertyRepository) {
//        this.propertyRepository = propertyRepository;
//    }
//
//    public Property saveProperty(Property property) {
//        return propertyRepository.save(property);
//    }
//
//    public List<Property> getAllProperties() {
//
//        return propertyRepository.findAll();
//    }
//
//    public Optional<Property> getPropertyById(Long id) {
//        return propertyRepository.findById(id);
//    }
//
//    public void deleteProperty(Long id) {
//
//        propertyRepository.deleteById(id);
//    }
//
//    public List<?> searchHotels(String CityName) {
//      return propertyRepository.SearchHotel(CityName);
//    }
//}

package com.hms.com.hms.Service;

import com.hms.com.hms.Entity.Property;
import com.hms.com.hms.Repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    public List<Property> searchHotels(String name) {
        return propertyRepository.searchHotel(name);
    }
}

