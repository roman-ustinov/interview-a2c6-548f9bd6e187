package sk.ustinov.animals.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ustinov.animals.dto.AnimalDto;
import sk.ustinov.animals.dto.AnimalWithDetailsDto;
import sk.ustinov.animals.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
@Tag(name = "Animals", description = "Operations related to animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(final AnimalService animalService) {
        this.animalService = animalService;
    }

    @Operation(
            summary = "Create a new animal",
            description = "Adds a new animal to the database",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Animal created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDto> createAnimal(@Valid @RequestBody AnimalDto animalDto) {
        AnimalDto createdAnimal = animalService.createAnimal(animalDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
    }

    @Operation(
            summary = "Get all animals",
            description = "Retrieves a list of all animals",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of animals retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalDto.class)))
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDto>> getAnimals() {
        List<AnimalDto> animals = animalService.getAnimals();
        return ResponseEntity.ok(animals);
    }

    @Operation(
            summary = "Get an animal by ID",
            description = "Retrieves an animal's details by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalDto.class))),
                    @ApiResponse(responseCode = "404", description = "Animal not found")
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDto> getAnimal(
            @Parameter(description = "ID of the animal to retrieve")
            @PathVariable Long id) {
        AnimalDto animal = animalService.getAnimal(id);
        return ResponseEntity.ok(animal);
    }

    @Operation(
            summary = "Get animals with details",
            description = "Retrieves a list of all animals along with detailed breed information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of animals with details retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalWithDetailsDto.class)))
            }
    )
    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalWithDetailsDto>> getAnimalsWithDetails() {
        List<AnimalWithDetailsDto> animalsWithDetails = animalService.getAnimalsWithDetails();
        return ResponseEntity.ok(animalsWithDetails);
    }

    @Operation(
            summary = "Update an existing animal",
            description = "Updates the details of an existing animal",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal updated successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalDto.class))),
                    @ApiResponse(responseCode = "404", description = "Animal not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDto> updateAnimal(@Valid @RequestBody AnimalDto animalDto) {
        AnimalDto updatedAnimal = animalService.updateAnimal(animalDto);
        return ResponseEntity.ok(updatedAnimal);
    }

    @Operation(
            summary = "Delete an animal",
            description = "Deletes an animal by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Animal deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Animal not found")
            }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAnimal(
            @Parameter(description = "ID of the animal to delete")
            @PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}