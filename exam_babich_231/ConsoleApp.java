package exam_Babich_231;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleApp {
    Scanner sc = new Scanner(System.in);
    Scanner scName = new Scanner(System.in);
    Scanner scSecondName = new Scanner(System.in);
    Scanner scSalary = new Scanner(System.in);
    Scanner scDoljnost = new Scanner(System.in);



    private int value;

    public ConsoleApp() throws SQLException, ClassNotFoundException {
        start();
    }


    int start() throws SQLException, ClassNotFoundException {
        DataBase db = new DataBase();
        db.isConnection();
        db.createDb("workers");
        while(true){

            System.out.println("***Инструкция***");
            System.out.println("1. Добавить сотрудника");
            System.out.println("2. Получить список всех сотрудников");
            System.out.println("3. Вывести всех сотрудников у которых зарплата больше 100 000 рублей");
            System.out.println("4. Закрыть приложение");

            value = sc.nextInt();


            if (value == 1) {
                System.out.println("Напишите значение ID: ");
                int id = sc.nextInt();
                System.out.println("Напишите имя сотрудника: ");
                String name = scName.nextLine();
                System.out.println("Напишите фамилию сотрудника: ");
                String secondName = scSecondName.nextLine();
                System.out.println("Напишите должность сотрудника ");
                String doljnost = scDoljnost.nextLine();
                System.out.println("Напишите зарплату сотрудника");
                int salary = scSalary.nextInt();
                db.addWorker("workers", id, name, secondName, doljnost, salary);
            }

            if (value == 2) {
                System.out.println("Все работники");
                db.selectAll("workers");

            }
            if (value == 3) {
                System.out.println("Все работники с зарплатой более 100 000");
                db.selectMaxesSalary("workers");
            }

            if (value == 4) {
                return 1;
            }
        }
    }


}
