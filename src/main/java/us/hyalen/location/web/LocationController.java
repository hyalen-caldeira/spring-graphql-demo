package us.hyalen.location.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import us.hyalen.location.entity.Location;
import us.hyalen.location.resolver.LocationResolver;
import us.hyalen.location.service.LocationService;

import java.util.List;

@RestController
public class LocationController {
    private LocationService locationService;
    private LocationResolver locationResolver;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setLocationResolver(LocationResolver locationResolver) {
        this.locationResolver = locationResolver;
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> list = locationService.retrieveLocations();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/locations/names")
    public ResponseEntity<List<String>> getAllNames() {
        List<String> list = locationService.findAllNames();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/locations/addresses")
    public ResponseEntity<List<String>> getAllAddresses() {
        List<String> list = locationService.findAllAddresses();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/locations/{id}/name")
    public ResponseEntity<String> getNameById(@PathVariable Long id) {
        String name = locationService.findNameById(id);

        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @GetMapping("/locations/resolver/{id}")
    public ResponseEntity<Location> findLocationUsingResolver(@PathVariable Long id) {
        Location location = locationResolver.findLocationById(id);

        return new ResponseEntity<>(location, HttpStatus.OK);
    }
}
