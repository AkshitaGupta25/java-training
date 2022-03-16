package Twitter;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/*
Ex:
(i) Make a POST request and fetch the response
from any POST API on reqres.in
documentation for RestTemplate :
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
 */
@RestController
public class TestController {

    @PostMapping("/")
    public JsonNode test(String id) {
        /* Get request from here to https://reqres.in/api/users

        We need to make a get request to url
        We also need to specify the header of the GET request
        key=value
        exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
			Class<T> responseType, Object... uriVariables)
         */
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> value = new ArrayList<>();
        value.add("application/json");
        httpHeaders.put("content-type", value);
        HttpEntity<JsonNode> entity = new HttpEntity<>(httpHeaders);
//        restTemplate.postForEntity()
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange("https://reqres.in/api/users",
                HttpMethod.POST, entity,
                JsonNode.class);
        return responseEntity.getBody();
//        System.out.println("inside controller");
//        throw new TweetNotFoundException();
    }
}