package ManueleSeretti.GestioneEventi.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfig {
    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
