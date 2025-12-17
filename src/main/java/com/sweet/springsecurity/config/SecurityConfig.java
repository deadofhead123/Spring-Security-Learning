package com.sweet.springsecurity.config;

import com.sweet.springsecurity.component.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF (cho demo)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ ADMIN vào được khu vực quản trị
                        .requestMatchers("/user/**").hasRole("USER")   // USER vào được khu vực chung
                        .anyRequest().authenticated()                  // Các yêu cầu khác đều phải xác thực
                )
//                .formLogin(withDefaults()) // Sử dụng form login mặc định
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .permitAll()           // Cấu hình logout
//                )
                .httpBasic() // Sử dụng basicAuth để login trong postman
        ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider); // Quyết định chọn DaoAuthenticationProvider làm Authentication Provider
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Mã hóa mật khẩu bằng BCrypt
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER") // Người dùng có quyền USER
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN") // Người dùng có quyền ADMIN
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}

