package animals.pets;

import animals.Animal;
import data.AnimalData;

import java.util.Scanner;

public class Dog extends Animal {
    private static final String type = AnimalData.DOG.toString();

    public Dog(Scanner scanner) {
        super(scanner,type);
    }


        @Override
        public void say() {
            System.out.println("Гав");

        }
    }
