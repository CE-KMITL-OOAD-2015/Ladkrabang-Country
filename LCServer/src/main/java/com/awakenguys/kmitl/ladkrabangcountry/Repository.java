package com.awakenguys.kmitl.ladkrabangcountry;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "places", path = "places")
public interface Repository extends MongoRepository<Place, String> {
    List<Place> findByNameLike(@Param("name") String name);
    List<Place> findByCategory(@Param("category") String category);
}
