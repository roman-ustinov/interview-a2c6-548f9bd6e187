package sk.ustinov.animals.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ustinov.animals.dto.AnimalDto;
import sk.ustinov.animals.dto.AnimalWithDetailsDto;
import sk.ustinov.animals.mapper.AnimalMapper;
import sk.ustinov.animals.model.Animal;
import sk.ustinov.animals.model.Breed;
import sk.ustinov.animals.repository.AnimalRepository;
import sk.ustinov.animals.repository.BreedRepository;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final BreedRepository breedRepository;
    private final AnimalMapper animalMapper;

    public AnimalServiceImpl(
            final AnimalRepository animalRepository,
            final BreedRepository breedRepository,
            final AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.breedRepository = breedRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    @Transactional
    public AnimalDto createAnimal(AnimalDto animalDto) {
        Breed breed = breedRepository.findById(animalDto.getBreedId())
                .orElseThrow(() -> new EntityNotFoundException("Breed not found"));

        Animal animal = animalMapper.toEntity(animalDto);
        animal.setBreed(breed);
        animalRepository.save(animal);

        return animalMapper.toDto(animal);
    }

    @Override
    public AnimalDto getAnimal(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found"));
        return animalMapper.toDto(animal);
    }

    @Override
    public List<AnimalDto> getAnimals() {
        return animalRepository.findAll().stream()
                .map(animalMapper::toDto)
                .toList();
    }

    @Override
    public List<AnimalWithDetailsDto> getAnimalsWithDetails() {
        return animalRepository.findAllWithBreed().stream()
                .map(animalMapper::toDetailsDto)
                .toList();
    }

    @Override
    @Transactional
    public AnimalDto updateAnimal(AnimalDto animalDto) {
        Animal animal = animalRepository.findById(animalDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Animal not found"));

        Breed breed = breedRepository.findById(animalDto.getBreedId())
                .orElseThrow(() -> new EntityNotFoundException("Breed not found"));

        animalMapper.updateEntityFromDto(animalDto, animal);
        animal.setBreed(breed);

        animal = animalRepository.save(animal);
        return animalMapper.toDto(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
