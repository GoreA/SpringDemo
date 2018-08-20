
package edu.pinkmotan.springsecurityspringmvc.config;

import edu.pinkmotan.springsecurityspringmvc.errorhandlers.ControlListAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author GoreA
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/css/**", "/index").permitAll()
            .and()
            .formLogin().loginPage("/login").failureUrl("/login-error") // in this project login page was ommited
            .and()
            .exceptionHandling().accessDeniedHandler(controlListAccessDeniedHandler())
            .and()
            .addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .inMemoryAuthentication()
            .withUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER"));
  }

  @Bean
  public AccessDeniedHandler controlListAccessDeniedHandler() {
    return new ControlListAccessDeniedHandler();
  }
}
