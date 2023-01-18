package com.learn.sharding.configuration;

import com.learn.sharding.student.entity.Student;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.learn.sharding.student.repository",
entityManagerFactoryRef = "studentEntityManagerFactory",
        transactionManagerRef = "studentTransactionManager"
)
public class StudentDataSourceConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.student")
    public DataSourceProperties studentDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.student.configuration")
    public DataSource studentDataSource() {
        return studentDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "studentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(studentDataSource())
                .packages(Student.class)
                .build();
    }

    @Primary
    @Bean(name = "studentTransactionManager")
    public PlatformTransactionManager studentTransactionManager(
            final @Qualifier("studentEntityManagerFactory") LocalContainerEntityManagerFactoryBean studentEntityManagerFactory) {
        return new JpaTransactionManager(studentEntityManagerFactory.getObject());
    }

}
