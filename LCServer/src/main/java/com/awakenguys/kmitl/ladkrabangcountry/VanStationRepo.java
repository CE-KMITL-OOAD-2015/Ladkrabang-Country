package com.awakenguys.kmitl.ladkrabangcountry;


import com.awakenguys.kmitl.ladkrabangcountry.model.Van_Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "van_stations", path = "van_stations")
public interface VanStationRepo extends MongoRepository<Van_Station, String> {
    List<Van_Station> findByName(@Param("name") String name);

}

