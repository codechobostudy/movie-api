package helper;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Loustler on 8/21/16.
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("io.codechobo.domain.member.repository")
@EntityScan("io.codechobo.domain.member")
@EnableTransactionManagement
public class JpaTestConfig {
}
