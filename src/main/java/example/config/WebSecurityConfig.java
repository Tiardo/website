
package example.config;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/registration").not().fullyAuthenticated()
                        .requestMatchers("/registration/**").not().fullyAuthenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/news", "/lk", "/static").hasRole("USER")
                        .requestMatchers("/LKabinet").hasRole("ADMIN")
                        .requestMatchers("/LKabinet/**").hasRole("USER")
                        .requestMatchers("/**").hasRole("USER")
                        .requestMatchers("/redirect","/redirect/**").hasRole("USER")
                        .requestMatchers("/videoStream").hasRole("USER")
                        .requestMatchers("/videoStream/**").hasRole("USER")
                        .requestMatchers("/", "/resources/**").permitAll()
                        .requestMatchers("/static").hasRole("USER")
                        .requestMatchers("/static/**").hasRole("USER")
                        .requestMatchers("/videos").hasRole("USER")
                        .requestMatchers("/videos/**").hasRole("USER")
                        .requestMatchers("/videoList").hasRole("USER")
                        .requestMatchers("/videoList/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        //.defaultSuccessUrl("/")
                        .permitAll()
                )



                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }





    private static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException {
            String username = authentication.getName();
            response.sendRedirect("/LKabinet/" + username);
        }
    }
}



