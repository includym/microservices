package kr.mz.samples.msa.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.mz.samples.msa.apigateway.model.Doodle;
import kr.mz.samples.msa.apigateway.util.rest.RestPageImpl;


@RestController
@RequestMapping(value="/doodles")
public class DoodleApiGatewayRestController {

	private static final String SERVICE_URI = "http://doodle-service";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.GET)
	public Page<Doodle> getDoodles(@RequestParam(value="toiletId", required=false) Long toiletId) {
		ParameterizedTypeReference<RestPageImpl<Doodle>> ptr = new ParameterizedTypeReference<RestPageImpl<Doodle>>() {};
		StringBuffer sb = new StringBuffer(SERVICE_URI);
		sb.append("/doodles");
		if(toiletId != null) {
			sb.append("/search/by-toilet-id?toiletId={toiletId}");
		}

		ResponseEntity<RestPageImpl<Doodle>> response = this.restTemplate.exchange(sb.toString(), HttpMethod.GET, null, ptr, toiletId);
		return response
				.getBody();
	}

}
