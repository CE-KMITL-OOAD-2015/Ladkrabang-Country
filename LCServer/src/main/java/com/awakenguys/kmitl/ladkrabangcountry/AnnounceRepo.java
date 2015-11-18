package com.awakenguys.kmitl.ladkrabangcountry;

import com.awakenguys.kmitl.ladkrabangcountry.model.Announce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "announces", path = "announces")
public interface AnnounceRepo extends MongoRepository<Announce, String> {

}

