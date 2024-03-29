package eg.sleepdiary;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import eg.sleepdiary.domain.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService; 

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .httpBasic().and()
    .authorizeRequests()
      .antMatchers(HttpMethod.POST, "/**").hasRole("USER")
      .antMatchers(HttpMethod.PUT, "/**").hasRole("USER")
      .antMatchers(HttpMethod.PATCH, "/**").hasRole("USER");
    // http.csrf().disable().cors().and().authorizeRequests()
    // .antMatchers(HttpMethod.GET, "*").permitAll()
    // .anyRequest().authenticated()
    // .and()
    // // Filter for the api/login requests
    // // .addFilterBefore(new LoginFilter("/login", authenticationManager()),
    // .addFilterBefore(new LoginFilter("*", authenticationManager()),
    //   UsernamePasswordAuthenticationFilter.class)
    // // Filter for other requests to check JWT in header
    // .addFilterBefore(new AuthenticationFilter(),
    //   UsernamePasswordAuthenticationFilter.class);
  }
  
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList("*"));
    config.setAllowedMethods(Arrays.asList("*"));
    config.setAllowedHeaders(Arrays.asList("*"));
    config.setAllowCredentials(true);
    config.applyPermitDefaultValues();
    
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
  }
}