package sk.ustinov.animals.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sk.ustinov.animals.dto.AnimalDto;
import sk.ustinov.animals.dto.AnimalWithDetailsDto;
import sk.ustinov.animals.model.Animal;

@Mapper(uses = {BreedMapper.class})
public interface AnimalMapper {

    @Mapping(source = "breed.id", target = "breedId")
    AnimalDto toDto(Animal animal);

    @Mapping(source = "breed.name", target = "breedName")
    AnimalWithDetailsDto toDetailsDto(Animal animal);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "breed", ignore = true)
    Animal toEntity(AnimalDto animalDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "breed", ignore = true)
    void updateEntityFromDto(AnimalDto animalDto, @MappingTarget Animal animal);
}