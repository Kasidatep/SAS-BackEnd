package sit.int221.sas;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ApplicationConfig {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

    }
