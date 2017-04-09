package lab.aikibo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan("lab.aikibo")
public class LearnJpaMultipleDbApplication implements CommandLineRunner{

	@Autowired
	DataSource sismiopDS;

	@Autowired
	DataSource dummyDS;

	public static void main(String[] args) {
		SpringApplication.run(LearnJpaMultipleDbApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("sismiop : " + sismiopDS);
		System.out.println("dummy : " + dummyDS);
	}
}
