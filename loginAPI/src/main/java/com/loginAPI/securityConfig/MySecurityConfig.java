package com.loginAPI.securityConfig;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableMethodSecurity
//public class MySecurityConfig{
////	@Autowired
////	CustomUserDetailService customUserDetailService;
////	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//		
//	}
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user=User.withUsername("kavita")
//				.password(passwordEncoder().encode("pass"))
//				.roles("NORMAL")
//				.build();
//		
////		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(user);
////		return inMemoryUserDetailsManager;
//		
//		//for database configuration
//		return customUserDetailService;
//		
//	}
	
	


//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity
//		.csrf().disable()
//		.authorizeHttpRequests().requestMatchers("/api/users/public")
//		.permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin();
//		return httpSecurity.build();
//
//	}
//
//}