package com.dslearn.dscatalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;

	//@Autowired
	//private UserDetailsService userDetailsService;

	//@Override
	//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	//}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/*/**");
	}

	//@Override
	//@Bean
	//protected AuthenticationManager authenticationManager() throws Exception {
	//	return super.authenticationManager();
	//}

}
