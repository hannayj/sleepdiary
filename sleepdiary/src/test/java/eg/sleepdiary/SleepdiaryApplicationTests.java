package eg.sleepdiary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import eg.sleepdiary.web.SleepPeriodApiController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SleepdiaryApplicationTests {
	
	@Autowired
	private SleepPeriodApiController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
