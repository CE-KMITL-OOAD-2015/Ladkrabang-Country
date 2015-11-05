package com.awakenguys.kmitl.ladkrabangcountry;

import com.awakenguys.kmitl.ladkrabangcountry.model.Train_Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "train_stations", path = "train_stations")
public interface TrainStationRepo extends MongoRepository<Train_Station, String> {
    List<Train_Station> findByName(@Param("name") String name);
}

