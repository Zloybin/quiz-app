package de.ezlobin.javarush.quiz.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/storage", "ezlobin", "Calambur26312!")){
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERT_NEW_NOUN);
            preparedStatement.setInt(1, 6);
            preparedStatement.setString(2, "das");
            preparedStatement.setString(3, "Fahrrad");
            preparedStatement.setString(4, "Велосипел");
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Произошла ошибка: " + ex.getMessage());
        }
    }
}
