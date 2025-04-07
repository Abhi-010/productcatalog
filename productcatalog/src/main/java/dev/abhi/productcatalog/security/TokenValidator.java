package dev.abhi.productcatalog.security;

import dev.abhi.productcatalog.dtos.TokenRequestDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {
    private RestTemplateBuilder restTemplateBuilder ;

    private final String baseUrl = "http://localhost:8081/auth/validateToken/" ;

    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder ;
    }

    /**
     * Calls user service to validate the token.
     * If token is not valid, optional is empty.
     * Else optional contains all of the data in payload
     * @param token
     * @return
     */

    public Optional<JwtObject> validateToken(Long id, String token){
        RestTemplate restTemplate = restTemplateBuilder.build() ;
        String requestUrl = baseUrl +  "{id}" ;

        TokenRequestDto requestBody = new TokenRequestDto();
        requestBody.setAuthToken(token);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TokenRequestDto> requestForEntity = new HttpEntity<>(requestBody,httpHeaders) ;

        ResponseEntity<SessionStatus> response =
                restTemplate.exchange(requestUrl, HttpMethod.GET, requestForEntity
                        ,SessionStatus.class,id) ;


        SessionStatus sessionStatus = response.getBody();
        if(sessionStatus.equals(SessionStatus.ENDED)){
            System.out.println("not serve this request");
        }

        System.out.println("Session Status : " + sessionStatus);
        return Optional.empty();
    }
}
