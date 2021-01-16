/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.demo;

/**
 *
 * @author cecilie
 */
public class InsultDTO {
    private int number;
    private String language;
    private String insult;
    private String created;
    private int shown;
    private String createdby;
    private int active;
    private String comment;

    public InsultDTO(int number, String language, String insult, String created, int shown, String createdby, int active, String comment) {
        this.number = number;
        this.language = language;
        this.insult = insult;
        this.created = created;
        this.shown = shown;
        this.createdby = createdby;
        this.active = active;
        this.comment = comment;
    }

    public int getNumber() {
        return number;
    }

    public String getLanguage() {
        return language;
    }

    public String getInsult() {
        return insult;
    }

    public String getCreated() {
        return created;
    }

    public int getShown() {
        return shown;
    }

    public String getCreatedby() {
        return createdby;
    }

    public int getActive() {
        return active;
    }

    public String getComment() {
        return comment;
    }
    
    
}
