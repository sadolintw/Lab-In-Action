package me.lab.in.action.auth_server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Slf4j
@Configuration
@EnableWebSecurity
//@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsAuthenticationProvider customUserDetailsAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info(">> WebSecurityConfiguration.configure AuthenticationManagerBuilder={}", auth);
        auth.authenticationProvider(customUserDetailsAuthenticationProvider);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
////                .antMatchers("/oauth/**")
////                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin().and()
//                .httpBasic()
//        ;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();

//        http.requestMatchers().antMatchers("/oauth/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").authenticated();
    }

    @Bean
    public TokenStore tokenStore() {
        //JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(dataSource);
        return new CustomTokenStore();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        System.out.println(b.encode("123456"));
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("asdf");
        // 註解掉的原因是因為跟原本的一樣，但記錄一下如果需要特別調整可以在這調
        //jwtAccessTokenConverter.setAccessTokenConverter(new CustomAccessTokenConverter());
        return jwtAccessTokenConverter;
    }
}
