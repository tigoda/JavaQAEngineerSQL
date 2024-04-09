package animals.birds;

import animals.Animal;
import data.AnimalData;

import java.util.Scanner;

public class Duck extends Animal implements IFlying {

    private static final String type = AnimalData.DUCK.toString();

    public Duck(Scanner scanner) {
        super(scanner, type);
    }


    @Override
    public void say() {
        System.out.println("Кря");
    }

    @Override
    public void fly() {
        System.out.println("Я лечу");

    }
}