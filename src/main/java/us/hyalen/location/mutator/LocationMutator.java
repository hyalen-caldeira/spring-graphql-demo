package us.hyalen.location.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import us.hyalen.location.exception.LocationNameNotFoundException;
import us.hyalen.location.entity.Location;
import us.hyalen.location.repository.LocationRepository;
import us.hyalen.location.exception.LocationNotFoundException;

import java.util.Optional;

@Component
public class LocationMutator implements GraphQLMutationResolver {
    private LocationRepository locationRepository;

    public LocationMutator(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // The name of the method must match with the name of Query on location.graphqls file
    public Location newLocation(String name, String address) {
        Location location = new Location(name, address);
        locationRepository.save(location);

        return location;
    }

    // The name of the method must match with the name of Query on location.graphqls file
    public boolean deleteLocation(Long id) {
        locationRepository.deleteById(id);

        return true;
    }

    // The name of the method must match with the name of Query on location.graphqls file
    public Location updateLocationName(String newName, Long id) {
        Optional<Location> optionalLocation =
                locationRepository.findById(id);

        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setName(newName);
            locationRepository.save(location);

            return location;
        } else {
            throw new LocationNotFoundException("Location Not Found", id);
        }
    }

    public boolean deleteLocationByName(String name) {
        boolean deleted = false;
        Iterable<Location> allLocations = locationRepository.findAll();

        // Loop through all locations to check their name
        for (Location location:allLocations) {
            if (location.getName().equals(name)) {
                // Delete if the Name was found
                locationRepository.delete(location);
                deleted = true;
            }
        }

        // Throw an exception if the name doesn't exist
        if (!deleted) {
            throw new LocationNameNotFoundException("Location Not Found", name);
        }

        return deleted;
    }
}
