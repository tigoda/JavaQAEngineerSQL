package tables;

import animals.Animal;
import animals.exceptions.AnimalNotSupported;
import data.AnimalData;
import db.MySQLConnector;
import factory.AnimalFactory;
import utils.EnumConverter;
import utils.Validators;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class AnimalTable extends AbsTable {

    Scanner scanner= new Scanner(System.in);
    EnumConverter enumConverter = new EnumConverter();
    Validators validators =new Validators();

    public AnimalTable() {
        super("animals");
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("type", "varchar(15)");
        columns.put("name", "varchar(15)");
        columns.put("color", "varchar(15)");
        columns.put("weight", "int");
        columns.put("age", "int");
        create();
    }

    public void printAll() throws SQLException {
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        printByQuery(sqlQuery);

    }

    public void printByType(String type) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM %s WHERE type = '%s'",
                tableName, type);
        printByQuery(sqlQuery);
    }

    public void printById(Integer id) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM %s WHERE id = '%d'",
                tableName, id);
        printByQuery(sqlQuery);
    }

    private void printByQuery(String sqlQuery) throws SQLException {
        db = new MySQLConnector();
        ResultSet resultSet = db.executeRequestWithAnswer(sqlQuery);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        if (!resultSet.isBeforeFirst()) {
            System.out.println("В таблице нет данных");
            return;
        }
        while (resultSet.next()) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.println(resultSetMetaData.getColumnName(i) + ": " + resultSet.getString(i));
                }
            }
        }

    public void insert(Animal animal) {
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (type, name, color, weight, age) " +
                        "VALUES ('%s', '%s', '%s', '%d', '%d')",
                tableName, animal.getType(), animal.getName(),
                animal.getColor(), animal.getWeight(), animal.getAge());
        db.executeRequest(sqlQuery);
        db.close();
    }

    public void update(Long id) throws AnimalNotSupported {
        String animalTypeStr = "";
        db = new MySQLConnector();

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
            animal.setId(id);

            db = new MySQLConnector();
            String sqlQuery = String.format("UPDATE %s SET " +
                            "type='%s', name='%s', color='%s', weight='%d', age='%d' WHERE id = %d ",
                    tableName,
                    animal.getType(),
                    animal.getName(),
                    animal.getColor(),
                    animal.getWeight(),
                    animal.getAge(),
                    animal.getId());
            db.executeRequest(sqlQuery);
            db.close();
        }
}
