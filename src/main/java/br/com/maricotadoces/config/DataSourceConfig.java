package br.com.maricotadoces.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.username}")
    private String userNameDatabase;

    @Value("${spring.datasource.password}")
    private String passwordDatabase;

    @Value("${spring.datasource.url}")
    private String urlDatabase;

    @Primary
    @Bean
    public DataSource dataSource() {

        return DataSourceBuilder.create().url(urlDatabase)
                .username(userNameDatabase).password(passwordDatabase)
                .build();
    }
}
