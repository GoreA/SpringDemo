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

  @Autowired
  private ControlListAccessDeniedHandler controlListAccessDeniedHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .exceptionHandling().accessDeniedHandler(controlListAccessDeniedHandler)
            .and()
            .addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
  }
}
