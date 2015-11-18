package com.awakenguys.kmitl.ladkrabangcountry;

import java.util.List;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import com.awakenguys.kmitl.ladkrabangcountry.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "reviews", path = "reviews")
public interface ReviewRepo extends MongoRepository<Review, String> {
    List<Review> findByAuthorId(@Param("authorId") String authorId);
}

