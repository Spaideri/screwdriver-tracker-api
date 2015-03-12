package org.screwdriver.tracker.authorization;

import org.screwdriver.tracker.entity.ApiKey;
import org.screwdriver.tracker.exception.UnauthorizedException;
import org.screwdriver.tracker.repository.ApiKeyRepository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiKeyRequiredInterceptor implements HandlerInterceptor {

    public static final CharSequence SECURE_URI_IDENTIFIER = "secure/";
    public static final String API_KEY_HEADER_IDENTIFIER = "X-api-key";

    private ApiKeyRepository apiKeyRepository;

    public ApiKeyRequiredInterceptor(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(httpServletRequest.getRequestURI().contains(SECURE_URI_IDENTIFIER)) {
            String apiKey = httpServletRequest.getHeader(API_KEY_HEADER_IDENTIFIER);
            if(null != apiKey) {
                ApiKey key = apiKeyRepository.findByApiKey(apiKey);
                if(null == key || !apiKey.equals(key.getApiKey())) {
                    throw new UnauthorizedException("Invalid API key");
                }
            } else {
                throw new UnauthorizedException("No API key provided");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

