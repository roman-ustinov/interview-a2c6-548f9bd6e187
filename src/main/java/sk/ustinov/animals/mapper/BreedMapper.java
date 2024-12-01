package sk.ustinov.animals.mapper;

import org.mapstruct.Mapper;
import sk.ustinov.animals.model.Breed;

@Mapper
public interface BreedMapper {

    String toBreedName(Breed breed);
}
