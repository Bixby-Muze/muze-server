package com.muze.api.movie.service;

import com.muze.api.movie.common.ImageCaching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractMovie {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ImageCaching imageCaching;

    @Value("${api.url.apiBaseUrl}")
    protected String baseUrl;

    @Value("${api.key}")
    protected String key;
}
