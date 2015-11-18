package com.awakenguys.kmitl.ladkrabangcountry;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import com.awakenguys.kmitl.ladkrabangcountry.model.Review;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;
import org.springframework.boot.autoconfigure.data.rest.SpringBootRepositoryRestMvcConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@Configuration
public class RepositoryConfig extends SpringBootRepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config) {
        config.exposeIdsFor(Review.class, User.class);
        config.setDefaultPageSize(100);

    }
}