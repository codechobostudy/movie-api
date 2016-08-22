package helper;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Loustler on 8/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =  JpaTestConfig.class)
@ActiveProfiles(profiles = "test")
@Transactional
public class EntityIntegrationTestHelper {
}
