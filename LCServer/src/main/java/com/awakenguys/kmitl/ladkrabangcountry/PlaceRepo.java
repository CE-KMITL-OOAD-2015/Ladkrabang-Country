package com.awakenguys.kmitl.ladkrabangcountry;

import java.util.List;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "places", path = "places")
public interface PlaceRepo extends MongoRepository<Place, String> {
    List<Place> findByNameLikeIgnoreCaseOrderByNameAsc(@Param("name") String name);
    List<Place> findByCategoryLikeOrderByNameAsc(@Param("category") String category);
    List<Place> findByLatBetween(@Param("from") double from1,@Param("to") double to1,@Param("from") double from2,@Param("to") double to2);
    List<Place> findByLngBetween(@Param("from") double from,@Param("to") double to);

}

