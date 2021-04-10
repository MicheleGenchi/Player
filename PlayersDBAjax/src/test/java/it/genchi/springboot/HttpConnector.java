package it.genchi.springboot;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpConnector {

	private final RestTemplate restTemplate;
	
	HttpConnector() {
		restTemplate = new RestTemplate();
	}
	
	<T> ResponseEntity<T> callService(Object obj, Class<T> responseType) throws URISyntaxException {
		RequestEntity<?> request=new RequestEntity<>(obj, HttpMethod.GET, new URI(""));
		return restTemplate.exchange(request, responseType);
	}
}
