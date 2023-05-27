package sit.int221.sas;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sit.int221.sas.utils.ListMapper;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
            return new ModelMapper();
        }

    @Bean
    public ListMapper listMapper(){
        return new ListMapper();
    }
}
