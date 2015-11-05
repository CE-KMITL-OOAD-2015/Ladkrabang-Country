package com.awakenguys.kmitl.ladkrabangcountry;

import java.util.List;

import com.awakenguys.kmitl.ladkrabangcountry.model.Bus_Line;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bus_lines", path = "bus_lines")
public interface BusLineRepo extends MongoRepository<Bus_Line, String> {
    List<Bus_Line> findByLine(@Param("line") String line);
}

