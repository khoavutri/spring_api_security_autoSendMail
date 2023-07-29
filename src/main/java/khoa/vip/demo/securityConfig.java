package khoa.vip.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true
,prePostEnabled = true,jsr250Enabled = true)
public class securityConfig {
	@Autowired
	UserDetailsService userdetailsService;
	 

	    @Autowired
	    public void config(AuthenticationManagerBuilder auth)
	            throws Exception {
	        auth.userDetailsService(userdetailsService)
	                .passwordEncoder(new BCryptPasswordEncoder());
	    }
	
	    @Autowired
	    jwtTokenFilter filter;
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) 
	    		throws Exception {
	    	return configuration.getAuthenticationManager();
	    }
	
	   @Bean
	    public SecurityFilterChain config(HttpSecurity http) throws Exception {
		   http.cors(cors->cors.hashCode()).csrf(csrf -> csrf.disable()).
		   authorizeHttpRequests(authorize ->
		   authorize.requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
		   			.requestMatchers("/user/**").authenticated()
		   			.anyRequest().permitAll()
				   ).sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
		   .httpBasic(httpBasic -> httpBasic.hashCode());
		  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		   return http.build();
	   }
}
