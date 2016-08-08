package kr.mz.samples.msa.doodle;

import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

import kr.mz.samples.msa.doodle.model.Doodle;

@EnableEurekaClient
@SpringBootApplication
public class DoodleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoodleServiceApplication.class, args);
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

@RepositoryRestResource
interface DoodleRepository extends JpaRepository<Doodle, Long> {

	@RestResource(path="by-toiletId")
	Collection<Doodle> findByToiletId(@Param("toiletId") Long toiletId);

}
