package de.ezlobin.javarush.quiz.servlets;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.*;

@WebServlet("/database/*")
public class DataBaseEditorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        Reader reader = request.getReader();
        while (reader.ready()) {
            char read = (char) reader.read();
            sb.append(read);
        }

        String responseBody = sb.toString();
        String article;
        String word;
        String translation;
        try {
            Object obj = new JSONParser().parse(responseBody);
            JSONObject jo = (JSONObject) obj;
            article = (String) jo.get("article");
            word = (String) jo.get("word");
            translation = (String) jo.get("translation");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        int maxIndex = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://172.18.0.3:5432/storage", "ezlobin", "Calambur26312!")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQuery.GET_SIZE_NOUNS);
            if (resultSet.next()) {
                maxIndex = resultSet.getInt(1);
            } else {
                System.out.println("Запрос не вернул никакого результата!");
            }


            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERT_NEW_NOUN);
            preparedStatement.setInt(1, maxIndex + 1);
            preparedStatement.setString(2, article);
            preparedStatement.setString(3, word);
            preparedStatement.setString(4, translation);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Произошла ошибка: " + ex.getMessage());
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("Добавлено новое слово");
            writer.flush();
        }

    }
}


