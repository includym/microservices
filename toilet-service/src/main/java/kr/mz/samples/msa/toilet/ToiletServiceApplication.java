package kr.mz.samples.msa.toilet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
public class ToiletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToiletServiceApplication.class, args);
	}

}

@RefreshScope
@RestController
@RequestMapping(value="/toilets")
class AccountController {

	@RequestMapping(method=RequestMethod.GET)
	public String welcome() {
		return "no-toilets";
	}

}