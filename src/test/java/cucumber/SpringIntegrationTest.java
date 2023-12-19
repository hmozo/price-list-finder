package cucumber;

import com.indit.pricelist.PriceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@CucumberContextConfiguration
@TestPropertySource("classpath:application-test.yaml")
@SpringBootTest (classes = PriceApplication.class)
public class SpringIntegrationTest {
}
