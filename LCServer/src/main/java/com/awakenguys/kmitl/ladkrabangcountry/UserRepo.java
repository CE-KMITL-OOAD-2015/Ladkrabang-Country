package com.awakenguys.kmitl.ladkrabangcountry;

import java.util.List;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepo extends MongoRepository<User, String> {
    List<User> findUserByFbId(@Param("fbId") String fbId);
    List<User> findUserById(@Param("id") String id);

}

