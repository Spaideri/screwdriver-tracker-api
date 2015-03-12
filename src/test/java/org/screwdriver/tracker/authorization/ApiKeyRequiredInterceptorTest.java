package org.screwdriver.tracker.authorization;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.screwdriver.tracker.entity.ApiKey;
import org.screwdriver.tracker.exception.UnauthorizedException;
import org.screwdriver.tracker.repository.ApiKeyRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class ApiKeyRequiredInterceptorTest {

    private static final String API_KEY = "APIKEY123";
    private static final String REQUEST_URI = "/secure/trackers/1";

    @Mock
    private ApiKeyRepository apiKeyRepository;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private ApiKey apiKey;

    private ApiKeyRequiredInterceptor apiKeyRequiredInterceptor;

    @Before
    public void setup() {
        initMocks(this);
        apiKeyRequiredInterceptor = new ApiKeyRequiredInterceptor(apiKeyRepository);
        when(httpServletRequest.getRequestURI()).thenReturn(REQUEST_URI);
    }

    @Test(expected = UnauthorizedException.class)
    public void shouldThrowUnauthorizedOnMissingApiKey() throws Exception {
        when(httpServletRequest.getHeader(ApiKeyRequiredInterceptor.API_KEY_HEADER_IDENTIFIER)).thenReturn(null);
        apiKeyRequiredInterceptor.preHandle(httpServletRequest, httpServletResponse, null);
    }

    @Test(expected = UnauthorizedException.class)
    public void shouldThrowUnauthorizedWhenNoApiKeyInRepository() throws Exception {
        when(httpServletRequest.getHeader(ApiKeyRequiredInterceptor.API_KEY_HEADER_IDENTIFIER)).thenReturn(API_KEY);
        when(apiKeyRepository.findByApiKey(API_KEY)).thenReturn(null);
        apiKeyRequiredInterceptor.preHandle(httpServletRequest, httpServletResponse, null);
    }

    @Test
    public void shouldReturnTrueWhenValidApiKeyGiven() throws Exception {
        when(httpServletRequest.getHeader(ApiKeyRequiredInterceptor.API_KEY_HEADER_IDENTIFIER)).thenReturn(API_KEY);
        when(apiKeyRepository.findByApiKey(API_KEY)).thenReturn(apiKey);
        when(apiKey.getApiKey()).thenReturn(API_KEY);
        apiKeyRequiredInterceptor.preHandle(httpServletRequest, httpServletResponse, null);
    }


}
