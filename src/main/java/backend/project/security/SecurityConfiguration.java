package backend.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST = {
            //Login
            "/api/users/login/**",

            //Register
            "/api/users/register/**"

            //category
            ,"/api/categories/**"

            //products
            ,"/api/products/**"

            //reviews
            ,"/api/reviews/**"
    };

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Validar el Token
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests( auth-> auth
                 //       .anyRequest().permitAll()

                .requestMatchers(AUTH_WHITELIST).permitAll()

                .anyRequest().authenticated()

        );

        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();

    }



}
