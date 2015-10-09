package exchange.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
    
	 @Autowired
	    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .userDetailsService(userDetailsService)
	                .passwordEncoder(getShaPasswordEncoder());
	    }
    
   

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/**").permitAll()
        //          .antMatchers("/", "/login", "/regestration", "/main").permitAll()
          //        .antMatchers("/addMetal", "/viewMetal").access("hasRole('Admin')")
         //         .antMatchers("/addMetal**").access("isAuthenticated()")
         //         .antMatchers("/viewMetal**").access("isAuthenticated()")
           //       .antMatchers("/delete-{title}-metal**").access("isAuthenticated()")
               
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);

        http.authorizeRequests()
              //  .antMatchers("/viewMetal*").access("hasRole('ROLE_Admin')")
        //   .antMatchers("/", "/login", "/regestration", "/main").permitAll()
        //    .antMatchers("/addMetal*", "/viewMetal*")
        //      .hasAnyRole("Admin").anyRequest()
        //      .authenticated()
                .and()
                 .formLogin()
                .defaultSuccessUrl("/main", false);
   /*     
        http.authorizeRequests()
                .antMatchers("/", "/login", "/regestration", "/main").permitAll()
                .antMatchers("/addMetal*", "/viewMetal*")
                .hasAnyRole("ROLE_Admin").anyRequest()
                .authenticated().and();
 */
      
        http.exceptionHandling()
                .accessDeniedPage("/login");
        
    }
    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }

}