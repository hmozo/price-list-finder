package cucumber;

import com.indit.pricelist.PriceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest (classes = PriceApplication.class)
public class SpringIntegrationTest {
}
