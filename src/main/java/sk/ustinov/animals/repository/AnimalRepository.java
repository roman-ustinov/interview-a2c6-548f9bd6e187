package sk.ustinov.animals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.ustinov.animals.model.Animal;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a JOIN FETCH a.breed")
    List<Animal> findAllWithBreed();

    boolean existsByName(String name);
}
