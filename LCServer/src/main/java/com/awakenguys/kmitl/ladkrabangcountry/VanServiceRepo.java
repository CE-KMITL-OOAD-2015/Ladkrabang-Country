package com.awakenguys.kmitl.ladkrabangcountry;

import java.util.List;

import com.awakenguys.kmitl.ladkrabangcountry.model.Van_Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "van_service", path = "van_service")
public interface VanServiceRepo extends MongoRepository<Van_Service, String> {
    //List<Van_Service> findBySrcLike(@Param("src") String src);
   // List<Van_Service> findByDesLike(@Param("des") String des);
}

