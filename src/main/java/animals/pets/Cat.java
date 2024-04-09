package animals.pets;

import animals.Animal;
import data.AnimalData;

import java.util.Scanner;

public class Cat extends Animal {
    private static final String type = AnimalData.CAT.toString();

    public Cat(Scanner scanner) {
        super(scanner,type);
    }

    @Override
    public void say() {
        System.out.println("Мяу");

    }
}
