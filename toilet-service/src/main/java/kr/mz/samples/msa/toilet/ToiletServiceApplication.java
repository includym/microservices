package kr.mz.samples.msa.toilet;

import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import kr.mz.samples.msa.toilet.model.Toilet;

@EnableEurekaClient
@SpringBootApplication
public class ToiletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToiletServiceApplication.class, args);
	}

}

@Configuration
class ToiletConfiguration extends RepositoryRestConfigurerAdapter {

	//data rest 를 사용하면 @Id 속성이 반환되지 않는다. 아래처럼 설정하면 반환함. 
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Toilet.class);
	}
	
}

@Component
class DummyDataCLR implements CommandLineRunner {
	
	@Autowired
	private ToiletRepository toiletRepository;

	@Override
	public void run(String... args) throws Exception {
		Stream
			.of("홍창민", "윤정아", "홍지한", "홍가은")
			.forEach(name -> toiletRepository.save(new Toilet(name)));
		
		toiletRepository.findAll().forEach(System.out::println);
		toiletRepository.findByName("홍창민").forEach(System.out::println);
	}

}

@RepositoryRestResource
interface ToiletRepository extends JpaRepository<Toilet, Long> {

	@RestResource(path="by-name")
	Collection<Toilet> findByName(@Param("name") String name);

}