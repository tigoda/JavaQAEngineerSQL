package factory;

import animals.Animal;
import animals.birds.Duck;
import animals.exceptions.AnimalNotSupported;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalData;

import java.util.Scanner;

public class AnimalFactory {

    public AnimalFactory(Scanner scanner) {
        this.scanner = scanner;
    }

    private Scanner scanner = null;
    public Animal create(String animalData) throws AnimalNotSupported {
        return switch (animalData.toLowerCase().trim()) {
            case "cat" -> new Cat(scanner);
            case "dog" -> new Dog(scanner);
            case "duck" -> new Duck(scanner);
            default -> throw new AnimalNotSupported(AnimalData.valueOf(animalData));
        };
    }
}



