package us.hyalen.location.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import us.hyalen.location.exception.LocationNotFoundException;
import us.hyalen.location.entity.Location;
import us.hyalen.location.repository.LocationRepository;

import java.util.Optional;

@Component
public class LocationResolver implements GraphQLQueryResolver {
    private LocationRepository locationRepository;

    public LocationResolver(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // The name of the method must match with the name of Query on location.graphqls file
    public Iterable<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    // The name of the method must match with the name of Query on location.graphqls file
    public Location findLocationById(Long id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);

        return optionalLocation.orElseThrow(() -> new LocationNotFoundException("Location ID not found", id));
    }
}
