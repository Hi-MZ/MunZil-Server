package io.munzil.munzil.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.munzil.munzil.global.error.CustomAuthenticationEntryPoint;
import io.munzil.munzil.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                // users
                .antMatchers(HttpMethod.HEAD, "/users/nickname").permitAll()
                .antMatchers(HttpMethod.GET, "/users").authenticated()
                .antMatchers(HttpMethod.GET, "/users/feeds").authenticated()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/users/token").permitAll()
                .antMatchers(HttpMethod.PATCH, "/users").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/token").authenticated()
                .antMatchers(HttpMethod.PATCH, "/users/password").authenticated()
                .antMatchers(HttpMethod.DELETE, "/users/logout").authenticated()
                .antMatchers(HttpMethod.DELETE, "/users/leave").authenticated()

                // feeds & like
                .antMatchers(HttpMethod.GET, "/feeds/**").authenticated()
                .antMatchers(HttpMethod.POST, "/feeds/**").authenticated()
                .antMatchers(HttpMethod.PATCH, "/feeds/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/feeds/**").authenticated()

                // question
                .antMatchers(HttpMethod.GET, "/questions/**").authenticated()

                // follower & following
                .antMatchers(HttpMethod.GET, "/follows/**").authenticated()
                .antMatchers(HttpMethod.POST, "/follows/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/follows/**").authenticated()

                // swagger
                .antMatchers("/swagger*/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()

                .anyRequest().denyAll()

                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))

                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper))

                .and()
                .build();
    }
}
