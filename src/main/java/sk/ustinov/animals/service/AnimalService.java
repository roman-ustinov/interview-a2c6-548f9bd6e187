package sk.ustinov.animals.service;

import sk.ustinov.animals.dto.AnimalDto;
import sk.ustinov.animals.dto.AnimalWithDetailsDto;

import java.util.List;

public interface AnimalService {

    AnimalDto createAnimal(AnimalDto animalDto);

    AnimalDto getAnimal(Long id);

    List<AnimalDto> getAnimals();

    List<AnimalWithDetailsDto> getAnimalsWithDetails();

    AnimalDto updateAnimal(AnimalDto animalDto);

    void deleteAnimal(Long id);
}
