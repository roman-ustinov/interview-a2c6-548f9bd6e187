package sk.ustinov.animals.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.ustinov.animals.dto.AnimalDto;
import sk.ustinov.animals.dto.AnimalWithDetailsDto;
import sk.ustinov.animals.mapper.AnimalMapper;
import sk.ustinov.animals.model.Animal;
import sk.ustinov.animals.model.Breed;
import sk.ustinov.animals.repository.AnimalRepository;
import sk.ustinov.animals.repository.BreedRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {

    private AnimalServiceImpl animalService;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private BreedRepository breedRepository;

    @Mock
    private Animal animal;

    @Mock
    private Breed breed;

    @Mock
    private AnimalDto animalDto;

    @Mock
    private AnimalWithDetailsDto animalWithDetailsDto;

    @Mock
    private final AnimalMapper animalMapper = Mappers.getMapper(AnimalMapper.class);

    @BeforeEach
    void init() {
        Mockito.reset(animalRepository, breedRepository, animalMapper, animal, breed, animalDto, animalWithDetailsDto);
        animalService = new AnimalServiceImpl(animalRepository, breedRepository, animalMapper);
    }

    @Test
    void createAnimal_ShouldCreatedAnimal() {
        //GIVEN
        when(breedRepository.findById(0L)).thenReturn(Optional.of(breed));
        when(animalMapper.toEntity(animalDto)).thenReturn(animal);
        when(animalMapper.toDto(animal)).thenReturn(animalDto);

        //WHEN
        AnimalDto actual = animalService.createAnimal(animalDto);

        //THEN
        verify(animal, times(1)).setBreed(breed);
        verify(animalRepository, times(1)).save(animal);
        verify(breedRepository).findById(0L);
        verify(animalMapper).toEntity(animalDto);
        assertEquals(animalDto, actual);
    }

    @Test
    void createAnimal_ThenThrowException() {
        //GIVEN
        when(breedRepository.findById(0L)).thenThrow(EntityNotFoundException.class);

        //WHEN + THEN
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> animalService.createAnimal(animalDto));
    }

    @Test
    void getAnimal_ShouldReturnAnimal() {
        //GIVEN
        when(animalRepository.findById(0L)).thenReturn(Optional.of(animal));
        when(animalMapper.toDto(animal)).thenReturn(animalDto);

        //WHEN
        AnimalDto actual = animalService.getAnimal(0L);

        //THEN
        verify(animalRepository, times(1)).findById(0L);
        verify(animalMapper).toDto(animal);
        assertEquals(animalDto, actual);
    }

    @Test
    void getAnimal_ThenThrowException() {
        //GIVEN
        when(animalRepository.findById(1L)).thenReturn(Optional.empty());

        //WHEN + THEN
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> animalService.getAnimal(1L));

        verify(animalRepository, times(1)).findById(1L);
    }

    @Test
    void getAnimals_ShouldReturnListOfAnimals() {
        //GIVEN
        List<Animal> animals = List.of(animal);
        List<AnimalDto> animalDtos = List.of(animalDto);

        when(animalRepository.findAll()).thenReturn(animals);
        when(animalMapper.toDto(animal)).thenReturn(animalDto);

        //WHEN
        List<AnimalDto> actual = animalService.getAnimals();

        //THEN
        verify(animalRepository, times(1)).findAll();
        verify(animalMapper).toDto(animal);
        assertEquals(animalDtos, actual);
    }

    @Test
    void getAnimalsWithDetails_ShouldReturnListOfAnimalsWithDetails() {
        //GIVEN
        animalWithDetailsDto.setId(1L);
        animalWithDetailsDto.setName("Test Animal");
        animalWithDetailsDto.setAge(3);
        animalWithDetailsDto.setGender("MALE");
        animalWithDetailsDto.setBreedName("Test Breed");

        List<Animal> animals = List.of(animal);
        List<AnimalWithDetailsDto> animalDetailsDtos = List.of(animalWithDetailsDto);

        when(animalRepository.findAllWithBreed()).thenReturn(animals);
        when(animalMapper.toDetailsDto(animal)).thenReturn(animalWithDetailsDto);

        //WHEN
        List<AnimalWithDetailsDto> actual = animalService.getAnimalsWithDetails();

        //THEN
        verify(animalRepository, times(1)).findAllWithBreed();
        verify(animalMapper).toDetailsDto(animal);
        assertEquals(animalDetailsDtos, actual);
    }

    @Test
    void updateAnimal_ThenThrowExceptionWhenAnimalNotFound() {
        //WHEN + THEN
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> animalService.updateAnimal(animalDto));
    }

    @Test
    void updateAnimal_ThenThrowExceptionWhenBreedNotFound() {
        //WHEN + THEN
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> animalService.updateAnimal(animalDto));
    }

    @Test
    void deleteAnimal_ShouldDeleteAnimal() {
        //GIVEN
        doNothing().when(animalRepository).deleteById(0L);

        //WHEN
        animalService.deleteAnimal(0L);

        //THEN
        verify(animalRepository, times(1)).deleteById(0L);
    }
}

