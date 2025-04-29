package com.app.lunchDeals.configuration;

    import com.app.lunchDeals.service.RestaurantDetailsService;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    public class SecurityConfig {

        private final RestaurantDetailsService restaurantDetailsService;

        public SecurityConfig(RestaurantDetailsService restaurantDetailsService) {
            this.restaurantDetailsService = restaurantDetailsService;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/restaurants/register", "/api/restaurants/login").permitAll()
                    .anyRequest().authenticated()
                );
            http.httpBasic(AbstractHttpConfigurer::disable);
            return http.build();
        }

        @Bean
        public AuthenticationManager authManager(HttpSecurity http) throws Exception {
            AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
            authManagerBuilder
                .userDetailsService(restaurantDetailsService)
                .passwordEncoder(passwordEncoder());
            return authManagerBuilder.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }