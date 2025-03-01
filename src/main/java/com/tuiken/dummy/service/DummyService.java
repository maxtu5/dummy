package com.tuiken.dummy.service;

import com.tuiken.dummy.model.DummyDto;
import com.tuiken.dummy.model.UrlDto;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Service
public class DummyService {

    @Value("${dummy.source.url:hst}")
    private String rsHost;
    @Value("${dummy.source.port:33}")
    private String rsPort;

    private static final String SMALL_PATH = "http://%s:%s/data/monarchs/random";

    private RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void init() {
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
    }

    public DummyDto getPerson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UrlDto> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                String.format(SMALL_PATH, rsHost, rsPort), GET, requestEntity, String.class);
        JSONObject responceObject = new JSONObject(response.getBody());
        DummyDto retval = new DummyDto();
        retval.setName(responceObject.getString("name"));
        retval.setChildren(responceObject.getJSONObject("family").getJSONArray("children").length());
        return retval;
    }
}
