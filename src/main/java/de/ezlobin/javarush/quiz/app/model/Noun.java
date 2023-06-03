package de.ezlobin.javarush.quiz.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nouns", schema= "app_schema")
@Getter @Setter @ToString
@Builder @AllArgsConstructor @NoArgsConstructor

public class Noun {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "article", length = 3)
    private String article;
    @Column(name = "word")
    private String word;
    @Column(name = "translation")
    private String translation;



    public Noun( String article, String word, String translation) {
        this.article = article;
        this.word = word;
        this.translation = translation;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }


}
