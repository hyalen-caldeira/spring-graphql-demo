package us.hyalen.location.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.hyalen.location.entity.Location;
import us.hyalen.location.exception.LocationNameNotFoundException;
import us.hyalen.location.exception.LocationNotFoundException;
import us.hyalen.location.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> retrieveLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    public List<String> findAllNames() {
        return locationRepository.findAllNames();
    }

    @Override
    public List<String> findAllAddresses() {
        return locationRepository.findAllAddresses();
    }

    @Override
    public String findNameById(Long id) {
        Optional<String> optionalName = Optional.ofNullable(locationRepository.findNameById(id));
        return optionalName.orElseThrow(() -> new LocationNameNotFoundException("Message", id.toString()));
    }
}
