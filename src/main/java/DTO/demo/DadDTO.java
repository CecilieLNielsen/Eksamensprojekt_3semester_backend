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
public class DadDTO {

    private String id;
    private String joke;
    private int status;

    public DadDTO(String id, String joke, int status) {
        this.id = id;
        this.joke = joke;
        this.status = status;
    }

    public String getJoke() {
        return joke;
    }

}
