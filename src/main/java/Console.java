import animals.Animal;
import animals.exceptions.AnimalNotSupported;
import data.AnimalData;
import data.CommandsData;
import db.MySQLConnector;
import factory.AnimalFactory;
import tables.AnimalTable;
import utils.EnumConverter;
import utils.Validators;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    public void consoleCall() throws AnimalNotSupported, SQLException {
        List<Animal> animals = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Validators validators = new Validators();
        EnumConverter enumConverter = new EnumConverter();
        AnimalTable animalTable = new AnimalTable();


        while (true) {
            System.out.printf("Введите нужную команду: %s\n ", enumConverter.getNamesFromEnum(CommandsData.class,
                    "/"));
            String commandStr = scanner.next().toUpperCase().trim();
            if (!validators.checkValueInEnum(CommandsData.class, commandStr)) {
                System.out.printf("Такой команды не существует %s\n", commandStr);
                continue;
            }

            CommandsData commandsData = CommandsData.valueOf(commandStr);

            switch (commandsData) {
                case ADD: {
                    String animalTypeStr = "";

                    while (true) {
                        System.out.printf("Введите тип животного: %s\n", enumConverter.getNamesFromEnum(AnimalData.class,
                                "/"));
                        animalTypeStr = scanner.next().toUpperCase().trim();

                        if (validators.checkValueInEnum(AnimalData.class, animalTypeStr)) {
                            break;
                        }
                        System.out.printf("Такого типа животного не существует %s\n", animalTypeStr);
                    }
                    Animal animal = new AnimalFactory(scanner).create(animalTypeStr);

                    animal.setType();
                    animal.setName();
                    animal.setAge();
                    animal.setColor();
                    animal.setWeight();
                    animals.add(animal);
                    animalTable.insert(animal);


                }
                case LIST: {
                    if (animals.isEmpty()) {
                        System.out.print("Список животных пустой\n");
                        break;
                    }

                    animals.forEach((Animal animal) -> System.out.println(animal.toString()));
                    break;
                }
                case PRINTALL: {
                    animalTable.printAll();
                    break;
                }
                case UPDATEANIMAL:{
                    String animalIdStr = "";
                    while (true) {
                        System.out.print("Введите id животного: \n");
                        animalIdStr = scanner.next().toUpperCase().trim();
                        if (validators.isStringNumber(animalIdStr) && MySQLConnector.isRecordExist("id",animalIdStr)) {
                            System.out.print("Это животное редактировать\n");
                            animalTable.printById(Integer.parseInt(animalIdStr));
                            System.out.print("yes/no?\n");
                            String answer = scanner.next().toUpperCase().trim();
                            if(answer.equals("YES")){
                                animalTable.update(Long.parseLong(animalIdStr));

                            }
                            break;
                        }
                        System.out.printf("Вы ввели несуществующий id %s\n", animalIdStr);
                    }

                break;
                }
                case PRINTBYTYPE:{
                    String typeAnimal = "";
                    System.out.printf("Введите тип животного: %s\n", enumConverter.getNamesFromEnum(AnimalData.class,
                            "/"));
                    typeAnimal = scanner.next().toUpperCase().trim();
                    animalTable.printByType(typeAnimal);
                    break;
                }
                case EXIT:


                    System.exit(0);
            }
        }
    }
}
