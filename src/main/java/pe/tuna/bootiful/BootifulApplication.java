package pe.tuna.bootiful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@ComponentScan(basePackages = "pe.tuna.*")
@EntityScan("pe.tuna.models")
@EnableJpaRepositories(basePackages = "pe.tuna.repository")
public class BootifulApplication {


    @GetMapping("/")
    public String index(){
        return "home/hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(BootifulApplication.class, args);
    }
}
