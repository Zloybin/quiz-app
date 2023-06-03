package de.ezlobin.javarush.quiz.servlets;



import de.ezlobin.javarush.quiz.app.config_provider.PropertiesSessionFactoryProvider;
import de.ezlobin.javarush.quiz.app.config_provider.SessionFactoryProvider;
import de.ezlobin.javarush.quiz.app.model.Noun;
import de.ezlobin.javarush.quiz.app.repository.NounRepository;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/database/*")
public class DataBaseEditorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        Reader reader = request.getReader();
        while (reader.ready()) {
            char read = (char) reader.read();
            sb.append(read);
        }

        String responseBody = sb.toString();
        Noun noun = null;
        try {
            Object obj = new JSONParser().parse(responseBody);
            JSONObject jo = (JSONObject) obj;

            noun = Noun.builder()
                    .article((String) jo.get("article"))
                    .word((String) jo.get("word"))
                    .translation((String) jo.get("translation"))
                    .build();

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        SessionFactoryProvider sessionFactoryProvider = new PropertiesSessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        NounRepository nounRepository = new NounRepository(sessionFactory);
        nounRepository.add(noun);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.write(noun.toString());
            writer.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}


