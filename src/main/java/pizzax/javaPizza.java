package pizzax;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class javaPizza {

    @Bean
    public int feliiPizza() {
        return 8;
    }
    @Bean
    public Pizza valid(){
        return new Pizza();
    }
}
