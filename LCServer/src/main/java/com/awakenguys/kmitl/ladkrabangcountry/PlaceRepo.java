package com.awakenguys.kmitl.ladkrabangcountry;

import java.util.List;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "places", path = "places")
public interface PlaceRepo extends MongoRepository<Place, String> {
    List<Place> findByNameLikeOrderByNameAsc(@Param("name") String name);
    List<Place> findByCategoryOrderByNameAsc(@Param("category") String category);
    //List<Place> findByLocationNear(Point location, Distance distance);
}

