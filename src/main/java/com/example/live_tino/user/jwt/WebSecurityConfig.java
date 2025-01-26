package com.example.live_tino.user.jwt;

import com.example.live_tino.user.bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    GetUserDAOBean getUserDAOBean;

    @Value("${JWT_SECRET_KEY}")
    String secretKey;

    @Autowired
    public WebSecurityConfig(GetUserDAOBean getUserDAOBean) {
        this.getUserDAOBean = getUserDAOBean;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)// CSRF 비활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless 설정
                .addFilterBefore(new JwtFilter(secretKey, getUserDAOBean), UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(authorizeRequests ->
//                    authorizeRequests
//                            .requestMatchers("/user/save").permitAll()
//                            .requestMatchers("/user/login").permitAll()
//                            .requestMatchers("/user/logout").permitAll()
//                            .requestMatchers("/user/password").permitAll()
//                            .requestMatchers("/user").permitAll()
//                            .requestMatchers("/user/id").permitAll()
//                            .requestMatchers("/chat/create").permitAll()
//                            .requestMatchers("/chat/room").permitAll()
//                            .requestMatchers("/chat").permitAll()
//                            .requestMatchers("/chat/{chatRoomId}").permitAll()
//                            .requestMatchers("/chat/user/exit").permitAll()
//                            .requestMatchers("/chat/message").permitAll()
//                            .requestMatchers("/broadcast/all/{broadcastId}").permitAll()
//                            .requestMatchers("/broadcast/user/{userId}").permitAll()
//                            .requestMatchers("/broadcast").permitAll()
//                            .requestMatchers("/broadcast/join").permitAll()
//                            .requestMatchers("/broadcast/quit").permitAll()
//                            .requestMatchers("/broadcast/user").permitAll()
//                            .anyRequest().authenticated()
//                )
                // JWT 필터
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:5672", "127.0.0.1"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
