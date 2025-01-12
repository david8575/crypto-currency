package org.david.crytocurrency.http;

import lombok.RequiredArgsConstructor;
import org.david.crytocurrency.exception.UpbitClientException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpClient {
    private final RestTemplate restTemplate;

    public String execute(String uri, HttpMethod method, HttpHeaders headers) {
        try{
            return restTemplate.exchange(
                    uri,
                    method,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<String>() {}
            ).getBody();
        } catch (RestClientException e) {
            throw new UpbitClientException(e.getMessage());
        }
    }
}
