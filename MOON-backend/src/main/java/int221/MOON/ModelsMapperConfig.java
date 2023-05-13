package int221.MOON;

import int221.MOON.validation.ValidDateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ModelsMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    };

    @Bean
    public ListMapper listMapper() {
        return ListMapper.getInstance();
    }

    @Bean
    public ValidDateValidator validDateValidator() {
        return new ValidDateValidator();
    }
}
