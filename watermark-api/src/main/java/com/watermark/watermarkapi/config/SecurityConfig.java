package com.watermark.watermarkapi.config;

import com.watermark.watermarkapi.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final LogoutSuccessHandler logoutSuccessHandler;
	private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final AuthenticationFailureHandler customAuthenticationFailureHandler;


	public SecurityConfig(UserService userService,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			LogoutSuccessHandler logoutSuccessHandler,
			AuthenticationSuccessHandler customAuthenticationSuccessHandler,
			AuthenticationFailureHandler customAuthenticationFailureHandler) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.logoutSuccessHandler = logoutSuccessHandler;
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
		this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
				.and().cors()
				.and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/error**", "/", "/login**").permitAll()
				.and().formLogin()
				.failureHandler(customAuthenticationFailureHandler)
				.successHandler(customAuthenticationSuccessHandler)
				.and().logout()
				.logoutSuccessHandler(logoutSuccessHandler)
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true);
	}

}
