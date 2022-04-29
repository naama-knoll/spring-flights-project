//package com.example.demo.Controllers.Security;
//
//import com.example.demo.BusniessLogics.UserRole;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT \"username\", \"password\",true from Users where \"username\"=?")
//                .authoritiesByUsernameQuery("SELECT  Users.username,CONCAT('ROLE_',User_Roles.role_name) from " +
//                        "Users join User_Roles on Users.user_role=User_Roles.id where Users.username=?");
//    }
//
//
//    @Bean
//    public PasswordEncoder getpassEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .antMatchers("/admin/**").hasRole(String.valueOf(UserRole.ADMIN))
//                .antMatchers("/airline-company/**").hasAnyRole(String.valueOf(UserRole.AIRLINE_COMPANY),String.valueOf(UserRole.ADMIN))
//                .antMatchers("/customer/**").hasAnyRole(String.valueOf(UserRole.CUSTOMER),String.valueOf(UserRole.ADMIN))
//                .antMatchers("/**").permitAll() //allow any user to connect to the page
//                .and()
//                .formLogin();
//
//    }
//
//
//}
