package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import realm.UserRealm;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserRealm realm;
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests()
         .antMatchers("/css/**","/js/**","/plugins/**").permitAll()
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/html/login.html")     //登录页面
             .loginProcessingUrl("/loginvalidate") //登录认证地址
             .defaultSuccessUrl("/html/index.html", true)  //登陆成功后的地址
             .permitAll()
             .and()
         .logout().logoutUrl("/logout")
             .permitAll()
             .and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	throws Exception {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		auth.userDetailsService(realm).passwordEncoder(encoder);
	}
	
	//用于启用@Secured所必需的
	 @Bean
	 @Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	 }
	
	 
}