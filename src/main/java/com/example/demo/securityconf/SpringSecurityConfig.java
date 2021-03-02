package com.example.demo.securityconf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	 	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http.authorizeRequests().antMatchers("/admin/assets/datatable/datatables.css").permitAll().and().csrf()
//				.disable();

		http.authorizeRequests()
				
				// cho phép hiệu ứng, không chặn các file css,js,bootstrap
				.antMatchers("/", "/Dangky","/shop","/shop-detail/**","/cart").permitAll()
				
				.antMatchers("/resources/**", "/templates/**", "/static/**", "/css/**","/js/**", "/image/**", "/webfonts/**").permitAll()
				 
				.anyRequest().fullyAuthenticated().and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/", true).and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll().and().csrf().disable();
		
	}


	  @Override
	    public void configure(WebSecurity web) throws Exception {
	       web.ignoring().antMatchers("/css/**","/js/**","/image/**","/webfonts/**","/icon/**");
	    }
	
}