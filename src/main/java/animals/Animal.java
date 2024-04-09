package animals;

import data.ColorData;
import utils.EnumConverter;
import utils.Validators;
import java.util.Scanner;

public abstract class Animal {

    public Animal(Scanner scanner, String type) {
        this.scanner = scanner;
        this.type = type;
    }

    private Scanner scanner = null;
    private String name;
    private int age;
    private int weight;
    private String color;
    private long id;
    private String type;
    private final Validators validators = new Validators();
    private final EnumConverter enumConverter = new EnumConverter();

    public String getName() {
        return name;
    }

    public void setName() {
        String name = "";
        while (true) {
            System.out.println("Введите имя животного:");
            name = scanner.next().trim();
            if (validators.isStringChars(name)) {
                break;
            }
            System.out.println("Введите имя корректно");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        String ageStr = "";
        while (true) {
            System.out.println("Введите возратс животного:");
            ageStr = scanner.next().trim();
            if (validators.isStringNumber(ageStr) && Integer.parseInt(ageStr) > 0) {
                break;
            }
            System.out.println("Введите возраст корректно");
        }
        this.age = Integer.parseInt(ageStr);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight() {
        String weightStr = "";
        while (true) {
            System.out.println("Введите вес животного:");
            weightStr = scanner.next().trim();
            if (validators.isStringNumber(weightStr) && Integer.parseInt(weightStr) > 0) {
                break;
            }
            System.out.println("Введите вес корректно");
        }
        this.weight = Integer.parseInt(weightStr);;
    }

    public String getColor() {
        return color;
    }

    public void setColor() {
        String colorDataStr = "";
        while (true) {
            System.out.printf("Введите цвет животного из представленных: %s\n ",
                    enumConverter.getNamesFromEnum(ColorData.class,
                            ","));
            colorDataStr = scanner.next().toUpperCase().trim();
            if (validators.checkValueInEnum(ColorData.class, colorDataStr)) {
                break;
            }
            System.out.printf("Цвета %s не существует\n", colorDataStr);
        }
        this.color = colorDataStr;
    }

    public String getType(){
        return type;
    }
    public  void setType(){
        this.type = type;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id =id;
    }


    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {

        System.out.println("Я иду");
    }

    public void drink() {

        System.out.println("Я пью");
    }

    public void eat() {

        System.out.println("Я ем");
    }

    @Override
    public String toString() {
        return String.format("Привет!Меня зовут %s  %s, мне %d %s, я вешу - %d кг, мой цвет - %s",type, name,
                age, getCase(), weight, getColor());

    }

    private String getCase() {
        int remainder = age % 10;
        if (remainder == 1)
            return "год";
        else if (remainder == 0 || remainder >= 5)
            return "лет";
        else if (remainder >= 2)
            return "года";
        return "лет";

    }
}
