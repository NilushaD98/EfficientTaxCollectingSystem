package taxproject.taxpayingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaxPayingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxPayingServiceApplication.class, args);
	}

}
