//package com.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import javax.sql.DataSource;
//
//@Configuration
//public class UserDetailsConfig {
//
//    private final DataSource dataSource;
//
//    public UserDetailsConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
//        jdbcDao.setDataSource(dataSource);
//        return jdbcDao;
//    }
//}
