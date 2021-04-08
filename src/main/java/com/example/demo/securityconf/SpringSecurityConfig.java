package com.example.demo.securityconf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/image/**",
			"static/**",
			"/img/**",
			"/",	
			"/login",
			"/quan-ly-diem",
			"/assets/**"
	};
	
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
		http.csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/js/**", "/css/**", "/img/**" ,"/assets/**", "/public/**", "/index", "/", "/login").permitAll();
		http.authorizeRequests()
				
				// cho phép hiệu ứng, không chặn các file css,js,bootstrap
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				
				.antMatchers("/resources/**", "/templates/**", "/static/**", "/css/**","/js/**", "/image/**", "/webfonts/**,/assets/**").permitAll()
				 
				.anyRequest().fullyAuthenticated().and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/GV/GVBM", true).and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll().and().csrf().disable();
		
	}


	  @Override
	    public void configure(WebSecurity web) throws Exception {
	       web.ignoring().antMatchers("/css/**","/js/**","/image/**","/assets/**","/static/**");
	    }
	  
	  
}