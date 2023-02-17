package com.example.tobysspring.user.dao.c1.v5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;

@Configuration
public class DaoFactoryV5 {

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        Field[] fields = this.getClass().getFields();
        return  dataSource;
    }

    @Bean
    public UserDaoV5 userDao() {
        return new UserDaoV5(dataSource());
    }

}
