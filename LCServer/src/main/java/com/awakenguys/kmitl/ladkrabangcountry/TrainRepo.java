package com.awakenguys.kmitl.ladkrabangcountry;


import com.awakenguys.kmitl.ladkrabangcountry.model.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "train", path = "train")
public interface TrainRepo extends MongoRepository<Train, String> {
    List<Train> findByName (@Param("name") String name);
}

