package org.screwdriver.tracker.repository;

import org.screwdriver.tracker.entity.ApiKey;
import org.springframework.data.repository.CrudRepository;

public interface ApiKeyRepository extends CrudRepository<ApiKey, Long> {

    ApiKey findByApiKey(String apiKey);

}
