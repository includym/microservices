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

import kr.mz.samples.msa.apigateway.model.Doodle;


@RestController
@RequestMapping(value="/doodles")
public class DoodleApiGatewayRestController {

	private static final String serviceUri = "http://doodle-service";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Doodle> getDoodles(@RequestParam(value="toiletId", required=false) Long toiletId) {
		ParameterizedTypeReference<Resources<Doodle>> ptr = new ParameterizedTypeReference<Resources<Doodle>>() {};
		StringBuffer sb = new StringBuffer(serviceUri);
		sb.append("/doodles");
		if(toiletId != null) {
			sb.append("/search/by-toiletId?toiletId={toiletId}");
		}

		ResponseEntity<Resources<Doodle>> response = this.restTemplate.exchange(sb.toString(), HttpMethod.GET, null, ptr, toiletId);
		return response
				.getBody()
				.getContent();
	}

}
