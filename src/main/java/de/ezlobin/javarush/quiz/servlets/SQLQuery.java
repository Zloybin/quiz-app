package de.ezlobin.javarush.quiz.servlets;

public class SQLQuery {
    public static final String INSERT_NEW_NOUN = "INSERT INTO app_schema.nouns VALUES (?,?,?,?);";
    public static final String GET_SIZE_NOUNS = "SELECT MAX(id) FROM app_schema.nouns";
}
