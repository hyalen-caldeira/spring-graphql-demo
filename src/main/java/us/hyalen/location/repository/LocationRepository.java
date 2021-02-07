package us.hyalen.location.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.hyalen.location.entity.Location;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    @Query("SELECT loc.id, loc.name FROM Location loc WHERE loc.id = :id")
    String findNameById(Long id);

    @Query("SELECT loc.id, loc.name FROM Location loc")
    List<String> findAllNames();

    @Query("SELECT loc.id, loc.address FROM Location loc")
    List<String> findAllAddresses();
}
