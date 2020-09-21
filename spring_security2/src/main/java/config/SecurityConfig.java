package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import realm.UserRealm;
import controller.exception.LoginfailHandler;
import controller.exception.MyAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true,securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserRealm userrealm;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/html/errorpassword.html","/html/errorusername.html","/users/permissions","/html/login.html","/html/authImg","/loginvalidate","/css/**","/js/**","/plugins/**","/img/**","/fonts/**").permitAll() //不拦截
			.antMatchers("/html/roleadmin.html").hasRole("HEHEd")
			.antMatchers("/html/permissionadmin.html").hasAuthority("user:create")
			.anyRequest().authenticated()   //其他需要登录
			.and().exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler())//自定义403
			.and()
		.formLogin() 
		.failureHandler(new LoginfailHandler())
			.loginPage("/html/login.html")
			.loginProcessingUrl("/loginvalidate")
			.defaultSuccessUrl("/html/index.html")
			.permitAll()
			.and().rememberMe().key("java").and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/html/login.html")
			.and()
			.csrf().disable();                                            
	}
	
	@Autowired  
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {  
		auth.userDetailsService(userrealm).passwordEncoder(new StandardPasswordEncoder());
    }  
	
	
	
}
