package sk.ustinov.animals.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import sk.ustinov.animals.dto.AnimalDto;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createAnimal_ShouldReturnCreatedAnimal() throws Exception {
        mockMvc.perform(post("/api/v1/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prepareAnimalDto())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("La Paloma La Blanca"))
                .andExpect(jsonPath("$.age").value(4));
    }

    @Test
    void getAnimal_ShouldReturnAnimal() throws Exception {
        mockMvc.perform(get("/api/v1/animals/1003")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void getAnimals_ShouldReturnListOfAnimals() throws Exception {
        mockMvc.perform(get("/api/v1/animals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void updateAnimal_ShouldReturnUpdatedAnimal() throws Exception {
        AnimalDto animalDto = new AnimalDto();
        animalDto.setId(1001L);
        animalDto.setName("Updated Animal");
        animalDto.setAge(4);
        animalDto.setBreedId(1L);
        animalDto.setGender("FEMALE");

        mockMvc.perform(put("/api/v1/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animalDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Animal"));
    }

    @Test
    void deleteAnimal_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/animals/1002")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private static AnimalDto prepareAnimalDto() {
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("La Paloma La Blanca");
        animalDto.setAge(4);
        animalDto.setBreedId(1L);
        animalDto.setGender("FEMALE");
        return animalDto;
    }
}
