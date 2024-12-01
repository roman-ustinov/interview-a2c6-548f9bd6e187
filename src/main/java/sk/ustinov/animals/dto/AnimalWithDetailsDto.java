package sk.ustinov.animals.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for Animal with detailed breed information")
public class AnimalWithDetailsDto {

    @Schema(description = "Unique identifier of the animal", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the animal", example = "Buddy")
    private String name;

    @Schema(description = "Age of the animal", example = "3")
    private int age;

    @Schema(description = "Name of the breed associated with the animal", example = "Labrador")
    private String breedName;

    @Schema(description = "Gender of the animal (MALE or FEMALE)", example = "MALE")
    private String gender;

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

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "AnimalWithDetailsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breedName='" + breedName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

