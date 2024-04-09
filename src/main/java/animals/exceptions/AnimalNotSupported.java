package animals.exceptions;

import data.AnimalData;

public class AnimalNotSupported extends Exception {
    public AnimalNotSupported(AnimalData animalData){

        super(String.format("Животного %s не существует", animalData.name()));
    }

}
