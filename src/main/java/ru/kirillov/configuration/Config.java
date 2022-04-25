package ru.kirillov.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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
        config.setJdbcUrl("jdbc:h2:file:D:/dev/code/java/mydb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE");
        config.setUsername("sa");
        config.setPassword("");
        config.setDriverClassName("org.h2.Driver");
        return new HikariDataSource(config);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        var properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.show.sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.kirillov.entity");
        sessionFactory.setHibernateProperties(properties);
        sessionFactory.setAnnotatedClasses(ru.kirillov.entity.User.class);
        return sessionFactory;
//        return new LocalSessionFactoryBuilder(dataSource())
//                .addPackage("ru.kirillov.entity")
//                .addProperties(properties)
//                .addAnnotatedClass(ru.kirillov.entity.User.class)
//                .buildSessionFactory();
    }

    @Bean
    HibernateTransactionManager transactionManager() {
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return transactionManager;
    }
}
