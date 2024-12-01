package sk.ustinov.animals.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.ustinov.animals.mapper.AnimalMapper;
import sk.ustinov.animals.mapper.BreedMapper;

@Configuration
public class ServiceConfiguration {

    @Bean
    AnimalMapper animalMapper() {
        return Mappers.getMapper(AnimalMapper.class);
    }

    @Bean
    BreedMapper breedMapper() {
        return Mappers.getMapper(BreedMapper.class);
    }
}
