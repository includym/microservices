package kr.mz.samples.msa.apigateway.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.mz.samples.msa.apigateway.model.Toilet;


@RestController
@RequestMapping(value="/toilets")
public class ToiletApiGatewayRestController {

	private static final String SERVICE_URI = "http://toilet-service";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Toilet> getToilets(@RequestParam(value="name", required=false) String name) {
		ParameterizedTypeReference<Resources<Toilet>> ptr = new ParameterizedTypeReference<Resources<Toilet>>() {};
		StringBuffer sb = new StringBuffer(SERVICE_URI);
		sb.append("/toilets");
		if(name != null) {
			sb.append("/search/by-name?name={name}");
		}

		ResponseEntity<Resources<Toilet>> response = this.restTemplate.exchange(sb.toString(), HttpMethod.GET, null, ptr, name);
		return response
				.getBody()
				.getContent();
	}

}
