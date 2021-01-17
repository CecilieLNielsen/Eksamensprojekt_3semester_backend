/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author cecilie
 */
public class BreedInformationDTO {
    private String breed;
    private String info;
    private String wikipedia;

    public BreedInformationDTO(String breed, String info, String wikipedia) {
        this.breed = breed;
        this.info = info;
        this.wikipedia = wikipedia;
    }

    public String getBreed() {
        return breed;
    }

    public String getInfo() {
        return info;
    }

    public String getWikipedia() {
        return wikipedia;
    }
    
    
    
}
