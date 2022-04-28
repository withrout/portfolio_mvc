package ru.kirillov.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.kirillov")
public class Config {

    @Bean
    DataSource dataSource() {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:file:D:/dev/code/java/mydb");
        config.setUsername("sa");
        config.setPassword("");
        config.setDriverClassName("org.h2.Driver");
        return new HikariDataSource(config);
    }

    @Bean
    public SessionFactory sessionFactoryBean() {
        var properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "create");
        properties.setProperty(Environment.FORMAT_SQL, "true");
        return new LocalSessionFactoryBuilder(dataSource())
                .addPackage("ru.kirillov.entity")
                .addProperties(properties)
                .addAnnotatedClass(ru.kirillov.entity.User.class)
                .buildSessionFactory();
    }

    @Bean
    HibernateTransactionManager transactionManager() {
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean());
        return transactionManager;
    }
}
