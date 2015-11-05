package com.awakenguys.kmitl.ladkrabangcountry;

import com.awakenguys.kmitl.ladkrabangcountry.model.Motorcycle_Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "motorcycle_stations", path = "motorcycle_stations")
public interface MotorcycleStationRepo extends MongoRepository<Motorcycle_Station, String> {
    List<Motorcycle_Station> findByName(@Param("name") String name);

}

