package kr.mz.samples.msa.apigateway.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.mz.samples.msa.apigateway.model.Toilet;

@RestController
@RequestMapping(value="/toilets")
public class ToiletApiGatewayRestController {

	private static final String serviceUri = "http://toilet-service";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Toilet> getToilets() {
		ParameterizedTypeReference<Resources<Toilet>> ptr = new ParameterizedTypeReference<Resources<Toilet>>() {};

		ResponseEntity<Resources<Toilet>> response = this.restTemplate.exchange(serviceUri + "/toilets", HttpMethod.GET, null, ptr);
		return response
				.getBody()
				.getContent();
	}

}
