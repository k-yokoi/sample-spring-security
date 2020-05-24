package net.kyokoi.sample.spring.security.chat.config;

import net.kyokoi.sample.spring.security.chat.app.web.security.CustomUserDetailsService;
import net.kyokoi.sample.spring.security.chat.app.web.security.LoginSuccessHandler;
import net.kyokoi.sample.spring.security.chat.app.web.security.SessionExpiredDetectingLoginUrlAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/static/**").permitAll()
            .antMatchers("/timeout").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .formLogin()
            .loginProcessingUrl("/authenticate")
            .loginPage("/login")
            .successHandler(loginSuccessHandler())
            .failureUrl("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .and()
            .logout()
            .logoutSuccessUrl("/login")
            .permitAll();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new SessionExpiredDetectingLoginUrlAuthenticationEntryPoint("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder);
    }
}
