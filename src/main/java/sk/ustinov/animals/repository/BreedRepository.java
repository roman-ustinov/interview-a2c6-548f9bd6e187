package sk.ustinov.animals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.ustinov.animals.model.Breed;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
}