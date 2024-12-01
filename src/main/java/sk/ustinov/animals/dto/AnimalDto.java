package sk.ustinov.animals.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Data Transfer Object for Animal operations")
public class AnimalDto {

    @Schema(description = "Unique identifier of the animal", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Schema(description = "Name of the animal", example = "Buddy")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be greater than 0")
    @Schema(description = "Age of the animal (must be greater than 0)", example = "3")
    private int age;

    @NotNull(message = "Breed ID is required")
    @Schema(description = "ID of the breed associated with the animal", example = "5")
    private Long breedId;

    @NotNull(message = "Gender is required")
    @Pattern(regexp = "MALE|FEMALE", message = "Gender must be MALE or FEMALE")
    @Schema(description = "Gender of the animal (MALE or FEMALE)", example = "MALE")
    private String gender;

    public AnimalDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "AnimalDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breedId=" + breedId +
                ", gender='" + gender + '\'' +
                '}';
    }
}
