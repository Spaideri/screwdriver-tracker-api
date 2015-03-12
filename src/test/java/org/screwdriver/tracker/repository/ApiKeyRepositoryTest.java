package org.screwdriver.tracker.repository;

import com.googlecode.flyway.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.screwdriver.tracker.config.DataSourceConfig;
import org.screwdriver.tracker.entity.ApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfig.class })
public class ApiKeyRepositoryTest {

    private static final String API_KEY = "APIKEY123";

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private Flyway flyway;

    @Before
    public void setup() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldFindApiKey() {
        ApiKey apiKey = apiKeyRepository.findByApiKey(API_KEY);

        assertEquals(API_KEY, apiKey.getApiKey());
    }

}
