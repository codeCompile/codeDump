package aristoweb.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import aristoweb.springboot.configuration.JpaConfiguration;


/*@Import(JpaConfiguration.class)
@EnableAutoConfiguration
@Configuration
@ComponentScan as @SpringBootApplication means all 3*/
//@SpringBootApplication(scanBasePackages={"aristoweb"})
@SpringBootApplication(scanBasePackages={"aristoweb"})
@ComponentScan({"aristoweb"})
//@EntityScan("aristoweb")
//@EnableJpaRepositories("aristoweb")
public class SpringBootStartServlet extends SpringBootServletInitializer 
{
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SpringBootStartServlet.class);
  }

  public static void main(String[] args) {
      SpringApplication.run(SpringBootStartServlet.class, args);
  }
}