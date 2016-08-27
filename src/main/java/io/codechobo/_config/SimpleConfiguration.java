package io.codechobo._config;

import net.chandol.logjdbc.LogJdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
public class SimpleConfiguration {
    @Bean
    public DataSource dataSource(){
        EmbeddedDatabase h2DataSource = new EmbeddedDatabaseBuilder()
                .setType(H2)
                .build();

        return new LogJdbcDataSource(h2DataSource);
    }
}
