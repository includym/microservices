package kr.mz.samples.msa.doodle;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import kr.mz.samples.msa.doodle.model.Doodle;
import kr.mz.samples.msa.doodle.repository.DoodleRepository;
import kr.mz.samples.msa.doodle.service.DoodleService;

@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class DoodleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoodleServiceApplication.class, args);
	}

}

@RestController
@RequestMapping(value="/doodles")
class DoodleRestController {

	@Autowired
	private DoodleService doodleService;

	@HystrixCommand(fallbackMethod="getDefaultDoodles")
	@RequestMapping(method=RequestMethod.GET)
	public Page<Doodle> getDoodles(Pageable pageable) {
		return doodleService.findAll(pageable);
	}

	@HystrixCommand(fallbackMethod="getDefaultSearchByToiletId")
	@RequestMapping(method=RequestMethod.GET, value="/search/by-toilet-id")
	public Page<Doodle> searchByToiletId(@RequestParam("toiletId") Long toiletId, Pageable pageable) {
		return doodleService.findByToiletId(toiletId, pageable);
	}

	public Page<Doodle> getDefaultDoodles(Pageable pageable) {
		return new PageImpl<Doodle>(new ArrayList<>());
	}

	public Page<Doodle> getDefaultSearchByToiletId(Long toiletId, Pageable pageable) {
		return new PageImpl<Doodle>(new ArrayList<>());
	}

}

@Component
class DummyDataCLR implements CommandLineRunner {

	@Autowired
	private DoodleRepository doodleRepository;

	@Override
	public void run(String... args) throws Exception {
		Stream
			.of("낙서", "낙서어어")
			.forEach(name -> doodleRepository.save(new Doodle(1L, name)));

		doodleRepository.findAll().forEach(System.out::println);
	}

}