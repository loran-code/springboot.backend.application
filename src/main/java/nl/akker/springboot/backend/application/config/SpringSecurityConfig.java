package nl.akker.springboot.backend.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public CustomUserDetailsService customUserDetailsService;

//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService);
//    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
        //Basic auth authentication
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index","index.html").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();


//        //JWT token authentication
//        http
//            .csrf().disable()
//            .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/customers/**").hasRole("USER")
//                .antMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/authenticated").authenticated()
//                .antMatchers(HttpMethod.GET,"/authenticate").permitAll()
//                .anyRequest().permitAll()
//                .and()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}