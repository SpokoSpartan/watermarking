package com.watermark.watermarkapi.config.security;

import com.watermark.watermarkapi.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final CustomLogoutSuccessHandler logoutSuccessHandler;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


	public SecurityConfig(UserService userService,
						  BCryptPasswordEncoder bCryptPasswordEncoder,
						  CustomLogoutSuccessHandler logoutSuccessHandler,
						  RestAuthenticationEntryPoint restAuthenticationEntryPoint,
						  CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
						  CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.logoutSuccessHandler = logoutSuccessHandler;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
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
				.and().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
				.and().cors()
				.and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/error*", "/", "/login*", "/swagger-ui.html", "/csrf",
						"/webjars/springfox-swagger-ui/**", "/swagger-resources/**", "/v2/api-docs**", "/image/get**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin()
				.failureHandler(customAuthenticationFailureHandler)
				.successHandler(customAuthenticationSuccessHandler)
				.and().logout()
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.logoutSuccessHandler(logoutSuccessHandler);
	}

}
