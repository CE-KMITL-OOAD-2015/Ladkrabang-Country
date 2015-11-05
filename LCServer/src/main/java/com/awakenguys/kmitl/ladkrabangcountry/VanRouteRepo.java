package com.awakenguys.kmitl.ladkrabangcountry;


import com.awakenguys.kmitl.ladkrabangcountry.model.Van_Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "van_routes", path = "van_routes")
public interface VanRouteRepo extends MongoRepository<Van_Route, String> {

}

