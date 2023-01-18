package com.learn.sharding.configuration;

import com.learn.sharding.teacher.entity.Teacher;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.learn.sharding.teacher.repository",
        entityManagerFactoryRef = "teacherEntityManagerFactory",
        transactionManagerRef = "teacherTransactionManager"
)
public class TeacherDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.teacher")
    public DataSourceProperties teacherDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.teacher.configuration")
    public DataSource teacherDataSource() {
        return teacherDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "teacherEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean teacherEntityManagerFactory(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(teacherDataSource())
                .packages(Teacher.class)
                .build();
    }

    @Bean(name = "teacherTransactionManager")
    public PlatformTransactionManager teacherTransactionManager(
            final @Qualifier("teacherEntityManagerFactory") LocalContainerEntityManagerFactoryBean teacherEntityManagerFactory) {
        return new JpaTransactionManager(teacherEntityManagerFactory.getObject());
    }
}
