package it.genchi.springboot;

import static org.hamcrest.CoreMatchers.is;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import it.genchi.springboot.model.Giocatore;
import it.genchi.springboot.model.Giocatori;
import it.genchi.springboot.service.PlayerServiceDB;
import it.genchi.springboot.utils.GiocatoreNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlayersApplication.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/test-application-context-player.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringBootDemoApplicationTests {
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootDemoApplicationTests.class);
	
	@Inject
	HttpConnector httpConnector;
	@Inject
	PlayerServiceDB playerService; 
	
	@Test
	public void testGiocatoriListSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8080/players/lista";
		URI uri = new URI(baseUrl);
		
	    //ResponseEntity<Giocatore> result = httpConnector.callService(Giocatore.class);
		ResponseEntity<Giocatori> result = restTemplate.getForEntity(uri, Giocatori.class);
		
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertTrue(result.getBody().getGiocatori().size()>0);
	}

	@Test
	public void testTrovaGiocatoreIDSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String url = "http://localhost:8080/players/trova/{id}";
		Map < String, String > params = new HashMap < String, String > ();
	    params.put("id", "1");

	    //ResponseEntity<Giocatore> result = httpConnector.callService(params,  Giocatore.class);
	    
		ResponseEntity<Giocatore> result = restTemplate.getForEntity(url, Giocatore.class, params);
		
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertTrue(result.getBody() instanceof Giocatore );
		// nel caso di eccezione (expected = GiocatoreNotFoundException.class)
		// Assert.assertNull(result.getBody());
	}
	

	@Test
	public void testAddPlayer() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080/players/new";
		URI uri = new URI(baseUrl);
		Giocatore giocatore = new Giocatore("c", "c", "c");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		 //ResponseEntity<Giocatore> result = httpConnector.callService(params,  Giocatore.class);

		HttpEntity<Giocatore> request = new HttpEntity<>(giocatore, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
		
		// Verify request succeed
		System.out.println(response.getBody());
		Assert.assertEquals(201, response.getStatusCodeValue());
		Assert.assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

	}

	@Test
    public void testUpdateGiocatoreIDSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080/players/aggiorna/{id}";
		Giocatore updatedInstance = new Giocatore(2L, "spring","t","t");
		Map < String, String > params = new HashMap < String, String > ();
	    params.put("id", "2");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		 //ResponseEntity<Giocatore> result = httpConnector.callService(params,  Giocatore.class);
		HttpEntity<Giocatore> requestUpdate = new HttpEntity<>(updatedInstance, headers);
		ResponseEntity<Giocatore> response=restTemplate.exchange(baseUrl, HttpMethod.PUT, requestUpdate, Giocatore.class, params);
		
		
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals(updatedInstance, response.getBody());
	}

	@Test
	public void testDeleteGiocatoreIDSuccess() throws URISyntaxException, GiocatoreNotFoundException {
		final String url  = "http://localhost:8080/players/delete/{id}";
		Map < String, String > params = new HashMap < String, String > ();
	    params.put("id", "6");
	    RestTemplate restTemplate = new RestTemplate();
	    
	    HttpHeaders headers = new HttpHeaders();
		headers.set("X-HTTP-Method-Override", "true");
		 //ResponseEntity<Giocatore> result = httpConnector.callService(params,  Giocatore.class);
	    HttpEntity<Giocatore> requestDelete = new HttpEntity<>(headers);
	    @SuppressWarnings("unused")
		ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.DELETE, requestDelete, String.class, params);
	}

	public static Logger getLog() {
		return log;
	}
	
}