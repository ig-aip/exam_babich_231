package exam_Babich_231;

import  java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final String host = "localhost";
    private final String port = "5432";
    private final String  dbName = "workHouse";
    private final String login = "postgres";
    private final String password = "jojo";

    private Connection dbCon;  //подключение дб

    private Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        dbCon = DriverManager.getConnection(str, login, password);
        return dbCon;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbCon = getDBConnection();
        if (dbCon.isValid(1000)) {
            System.out.println("Подключение успешно!");
        }
        else {
            System.out.println("Подключение не удачно!");
        }
    }

    public void createDb(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INT PRIMARY KEY, nameWorker VARCHAR(50), secondNameWorker VARCHAR(50), doljnost VARCHAR(50), salary INT);";
        Statement statment = getDBConnection().createStatement();
        statment.executeUpdate(sql);
    }

    public void addWorker(String table, int id, String nameWorker, String secondNameWorker, String doljnost, int salary){
        try{
            Statement statment = getDBConnection().createStatement();
            int rows = statment.executeUpdate("INSERT  INTO " + table + "(id, nameWorker, secondNameWorker, doljnost, salary) " + "VALUES (" + id +", '" + nameWorker + "', '"+ secondNameWorker +"', '"+ doljnost +"', " + salary +");");
            System.out.println("Добавлено\n");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Не удалось добавить");
        }
    }

    public void selectAll(String tableName) throws SQLException, ClassNotFoundException {
        Statement statment = getDBConnection().createStatement();
        ResultSet resultSet = statment.executeQuery("SELECT * from " + tableName);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String nameWorker = resultSet.getString(2);
            String secondWorker = resultSet.getString(3);
            String doljnost = resultSet.getString(4);
            int salary = resultSet.getInt(5);
            System.out.printf("%d. %s. %s. %s %d. \n", id, nameWorker, secondWorker, doljnost, salary);
        }
    }

    public void selectMaxesSalary(String tableName) throws SQLException, ClassNotFoundException {
        Statement statment = getDBConnection().createStatement();
        ResultSet resultSet = statment.executeQuery("SELECT * from "+ tableName +" WHERE salary >= 100000;");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String nameWorker = resultSet.getString(2);
            String secondWorker = resultSet.getString(3);
            String doljnost = resultSet.getString(4);
            int salary = resultSet.getInt(5);
            System.out.printf("%d. %s. %s. %s %d. \n", id, nameWorker, secondWorker, doljnost, salary);
        }
    }


}
