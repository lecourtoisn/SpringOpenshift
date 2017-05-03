import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SimpleController extends SpringBootServletInitializer {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Simple client";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
